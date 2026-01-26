package com.employeedb.employeedatabase.data.repository

import com.employeedb.employeedatabase.data.local.AttendanceDao
import com.employeedb.employeedatabase.data.local.AttendanceWithEmployee
import com.employeedb.employeedatabase.model.Attendance
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val dao: AttendanceDao
) : AttendanceRepository {
    override fun getRecentAttendance(employeeId: Long): Flow<List<Attendance>> {
        return dao.getRecentAttendance(employeeId)
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

    override fun getAllAttendanceByDate(date: Long): Flow<List<AttendanceWithEmployee>> {
        return dao.getAllAttendanceByDate(date)
    }

    override fun getAllAttendance(): Flow<List<Attendance>> {
        return dao.getAllAttendance()
    }

    override suspend fun attendanceExists(
        employeeId: Long,
        date: Long
    ): Boolean {
       return dao.attendanceExists(employeeId, date) > 0
    }

    override fun getRecentAttendanceWithNames(): Flow<List<AttendanceWithEmployee>> {
        return dao.getRecentAttendanceWithNames()
    }

}