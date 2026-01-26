package com.employeedb.employeedatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.employeedb.employeedatabase.data.local.AttendanceWithEmployee
import com.employeedb.employeedatabase.data.repository.AttendanceRepository
import com.employeedb.employeedatabase.data.repository.EmployeeRepository
import com.employeedb.employeedatabase.model.Attendance
import com.employeedb.employeedatabase.model.AttendanceStatus
import com.employeedb.employeedatabase.model.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val repository: AttendanceRepository,
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val _selectedDate = MutableStateFlow(getCurrentDateInMillis())
    val selectedDate: StateFlow<Long> = _selectedDate.asStateFlow()

    // all employees
    val employees: StateFlow<List<Employee>> = employeeRepository.getEmployees()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    // attendance for selected date
    val attendanceForDate: StateFlow<List<AttendanceWithEmployee>> =
        selectedDate.flatMapLatest { date ->
            repository.getAllAttendanceByDate(date)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val attendanceSummary: StateFlow<AttendanceSummary> = combine(
        employees, attendanceForDate
    ) { allEmployees, attendanceList ->
        AttendanceSummary(
            totalEmployees = allEmployees.size,
            marked = attendanceList.size,
            present = attendanceList.count { it.attendance.status == AttendanceStatus.PRESENT },
            late = attendanceList.count { it.attendance.status == AttendanceStatus.LATE },
            leave = attendanceList.count { it.attendance.status == AttendanceStatus.LEAVE }
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue = AttendanceSummary()
    )

    fun getRecentAttendance(employeeId: Long): StateFlow<List<Attendance>> =
        repository.getRecentAttendance(employeeId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )


    fun getAttendanceByDate(employeeId: Long, date: Long): StateFlow<Attendance?> {
        return repository
            .getAttendanceByDate(employeeId, date)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )
    }

    fun updateSelectedDate(date: Long) {
        _selectedDate.value = date
    }

    fun markAttendance(
        employeeId: Long,
        inTime: String?,
        outTime: String?,
        status: AttendanceStatus
    ) {
        viewModelScope.launch {
            val totalHours = if (inTime != null && outTime != null) {
                calculateTotalHours(inTime, outTime)
            } else null

            val attendance = Attendance(
                id = 0,
                employeeId = employeeId,
                date = selectedDate.value,
                inTime = inTime,
                outTime = outTime,
                totalHours = totalHours,
                status = status
            )
            repository.insertAttendance(attendance)
        }
    }

    suspend fun checkAttendanceExists(employeeId: Long, date: Long): Boolean {
        return repository.attendanceExists(employeeId, date)
    }

    fun insertAttendance(attendance: Attendance) {
        viewModelScope.launch {
            repository.insertAttendance(attendance)
        }
    }

    fun updateAttendance(attendance: Attendance) {
        viewModelScope.launch {
            repository.updateAttendance(attendance)
        }
    }

    fun deleteAttendance(attendance: Attendance) {
        viewModelScope.launch {
            repository.deleteAttendance(attendance)
        }
    }

    private fun getCurrentDateInMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }


    private fun calculateTotalHours(inTime: String, outTime: String): String {
        return try {
            val format = SimpleDateFormat("HH:mm", Locale.getDefault())
            val inDate = format.parse(inTime)
            val outDate = format.parse(outTime)

            if (inDate != null && outDate != null) {
                val diff = outDate.time - inDate.time
                val hours = TimeUnit.MICROSECONDS.toHours(diff)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60

                "${hours}h ${minutes}m"
            } else "0h 0m"
        } catch (e: Exception) {
            "0h 0m"
        }
    }
}

data class AttendanceSummary(
    val totalEmployees: Int = 0,
    val marked: Int = 0,
    val present: Int = 0,
    val late: Int = 0,
    val leave: Int = 0
)