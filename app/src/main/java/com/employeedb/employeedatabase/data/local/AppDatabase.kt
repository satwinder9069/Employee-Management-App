package com.employeedb.employeedatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.employeedb.employeedatabase.model.Attendance
import com.employeedb.employeedatabase.model.AttendanceConverters
import com.employeedb.employeedatabase.model.Employee

@Database(entities = [Employee::class, Attendance::class], version = 4, exportSchema = false)
@TypeConverters(AttendanceConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeeDao() : EmployeeDao
    abstract fun attendanceDao() : AttendanceDao
}