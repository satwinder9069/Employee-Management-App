package com.employeedb.employeedatabase.data.repository

import com.employeedb.employeedatabase.data.local.AttendanceDao
import com.employeedb.employeedatabase.model.Attendance
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val dao: AttendanceDao
) : AttendanceRepository {
    override fun getRecentAttendance(employeeId: Long): Flow<List<Attendance>> {
        return dao.getRecentAttendence(employeeId)
    }

    override fun getAttendanceByDate(
        employeeId: Long,
        date: Long
    ): Flow<Attendance?> {
        return dao.getAttendanceByDate(employeeId, date)
    }

    override suspend fun insertAttendance(attendance: Attendance) {
        dao.insertAttendance(attendance)
    }

    override suspend fun insertAll(attendanceList: List<Attendance>) {
        dao.insertAll(attendanceList)
    }

    override suspend fun updateAttendance(attendance: Attendance) {
        dao.updateAttendance(attendance)
    }

    override suspend fun deleteAttendance(attendance: Attendance) {
        dao.deleteAttendance(attendance)
    }

    override suspend fun deleteAttendanceByEmployee(employeeId: Long) {
        dao.deleteAttendanceForEmployee(employeeId)
    }

}