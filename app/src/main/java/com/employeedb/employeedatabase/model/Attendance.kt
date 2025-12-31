package com.employeedb.employeedatabase.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "attendance", indices = [Index("employeeId")])
data class Attendance(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val employeeId: Long,
    val date: Long,
    val inTime: String?,
    val outTime: String?,
    val totalHours: String?,
    val status: AttendanceStatus
)

enum class AttendanceStatus {
    PRESENT, LATE, LEAVE
}

class AttendanceConverters {
    @TypeConverter
    fun fromStatus(status: AttendanceStatus) : String {
        return status.name
    }

    @TypeConverter
    fun toStatus(value: String): AttendanceStatus {
        return AttendanceStatus.valueOf(value)
    }
}