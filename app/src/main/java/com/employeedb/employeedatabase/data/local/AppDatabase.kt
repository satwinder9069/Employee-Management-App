package com.employeedb.employeedatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.employeedb.employeedatabase.data.model.Attendance
import com.employeedb.employeedatabase.data.model.AttendanceConverters
import com.employeedb.employeedatabase.data.model.Employee

@Database(entities = [Employee::class, Attendance::class], version = 5, exportSchema = false)
@TypeConverters(AttendanceConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeeDao() : EmployeeDao
    abstract fun attendanceDao() : AttendanceDao
}