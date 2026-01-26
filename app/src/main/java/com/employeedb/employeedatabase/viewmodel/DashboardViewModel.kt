package com.employeedb.employeedatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.employeedb.employeedatabase.data.repository.AttendanceRepository
import com.employeedb.employeedatabase.data.repository.EmployeeRepository
import com.employeedb.employeedatabase.model.AttendanceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val employeeRepo: EmployeeRepository,
    private val attendanceRepo: AttendanceRepository
) : ViewModel() {

    private val todayDate = getTodayDateInMillis()

    //total employees
    val totalEmployee: StateFlow<Int> = employeeRepo.getEmployees().map { it.size }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

    // all employees for department count
    private val allEmployee = employeeRepo.getEmployees().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    // department count
    val departmentCount: StateFlow<Int> = allEmployee.map { it.size }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

    // today's attendance
    val todayAttendance = attendanceRepo.getAllAttendanceByDate(todayDate).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    // present today count
    val presentToday: StateFlow<Int> = todayAttendance.map { list ->
        list.count { it.attendance.status == AttendanceStatus.PRESENT }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

    // late arrivals today
    val lateToday: StateFlow<Int> = todayAttendance.map { list ->
        list.count { it.attendance.status == AttendanceStatus.LATE }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

    // On leave today
    val onLeaveToday: StateFlow<Int> = todayAttendance
        .map { list -> list.count { it.attendance.status == AttendanceStatus.LEAVE } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    // attendance percentage for this week
    val weekAttendancePercentage: StateFlow<Float> = combine(
        totalEmployee,
        todayAttendance
    ) { total, attendance ->

        if (total > 0) {
            attendance.size.toFloat() / total.toFloat()
        } else 0f
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0f
    )

    //recent activities (last 5 attendance records)
    val recentActivities = attendanceRepo
        .getRecentAttendanceWithNames()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private fun getTodayDateInMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
}