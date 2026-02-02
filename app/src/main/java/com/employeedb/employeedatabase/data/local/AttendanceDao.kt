package com.employeedb.employeedatabase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.employeedb.employeedatabase.data.model.Attendance
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
    @Query("""SELECT * FROM attendance WHERE employeeId = :employeeId ORDER BY date DESC LIMIT 5""")
    fun getRecentAttendance(
        employeeId: Long
    ) : Flow<List<Attendance>>

    @Query("""SELECT * FROM attendance WHERE employeeId = :employeeId AND date = :date LIMIT 1""")
    fun getAttendanceByDate(
        employeeId: Long,
        date: Long
    ): Flow<Attendance?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAttendance(
        attendance: Attendance
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        attendanceList: List<Attendance>
    )

    @Update
    suspend fun updateAttendance(attendance: Attendance)

    @Delete
    suspend fun deleteAttendance(
        attendance: Attendance
    )

    @Query("""
        DELETE FROM attendance WHERE employeeId = :employeeId
    """)
    suspend fun deleteAttendanceForEmployee(
        employeeId: Long
    )

    @Query("""
        SELECT a.*, e.name as employeeName, e.department 
        FROM attendance a 
        INNER JOIN employees e ON a.employeeId = e.id 
        WHERE a.date = :date 
        ORDER BY e.name ASC
    """)
    fun getAllAttendanceByDate(date: Long): Flow<List<AttendanceWithEmployee>>

    // Get all attendance records (for reports)
    @Query("""SELECT * FROM attendance ORDER BY date DESC""")
    fun getAllAttendance(): Flow<List<Attendance>>

    // Check if attendance exists
    @Query("""
        SELECT COUNT(*) FROM attendance 
        WHERE employeeId = :employeeId AND date = :date
    """)
    suspend fun attendanceExists(employeeId: Long, date: Long): Int

    @Query("""
        SELECT a.*, e.name as employeeName, e.department 
        FROM attendance a 
        INNER JOIN employees e ON a.employeeId = e.id 
        ORDER BY a.date DESC, a.id DESC 
        LIMIT 10
    """)
    fun getRecentAttendanceWithNames(): Flow<List<AttendanceWithEmployee>>
}
//for joined query
data class AttendanceWithEmployee(
    @Embedded val attendance: Attendance,
    val employeeName: String,
    val department: String
)