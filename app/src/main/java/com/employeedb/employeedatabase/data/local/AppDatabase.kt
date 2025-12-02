package com.employeedb.employeedatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.employeedb.employeedatabase.model.Employee

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao() : EmployeeDao
}