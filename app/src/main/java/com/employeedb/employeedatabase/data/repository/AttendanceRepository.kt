package com.employeedb.employeedatabase.data.repository

import com.employeedb.employeedatabase.data.local.AttendanceWithEmployee
import com.employeedb.employeedatabase.model.Attendance
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {
    fun getRecentAttendance(employeeId: Long) : Flow<List<Attendance>>

    fun getAttendanceByDate(employeeId: Long, date: Long) : Flow<Attendance?>

    suspend fun insertAttendance(attendance: Attendance)

    suspend fun insertAll(attendanceList: List<Attendance>)

    suspend fun updateAttendance(attendance: Attendance)

    suspend fun deleteAttendance(attendance: Attendance)

    suspend fun deleteAttendanceByEmployee(employeeId: Long)

    fun getAllAttendanceByDate(date: Long): Flow<List<AttendanceWithEmployee>>

    fun getAllAttendance(): Flow<List<Attendance>>

    suspend fun attendanceExists(employeeId: Long, date: Long): Boolean

    fun getRecentAttendanceWithNames(): Flow<List<AttendanceWithEmployee>>

}