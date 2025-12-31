package com.employeedb.employeedatabase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.employeedb.employeedatabase.model.Attendance
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
    @Query("""SELECT * FROM attendance WHERE employeeId = :employeeId ORDER BY date DESC LIMIT 5""")
    fun getRecentAttendence(
        employeeId: Long
    ) : Flow<List<Attendance>>

    @Query("""SELECT * FROM attendance WHERE employeeId = :employeeId AND date = :date LIMIT 1""")
    fun getAttendanceByDate(
        employeeId: Long,
        date: Long
    ): Flow<Attendance?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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
}