package com.employeedb.employeedatabase

import android.app.Application
import androidx.room.Room
import com.employeedb.employeedatabase.data.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmployeeApp : Application()

//when without hilt

//class EmployeeApp : Application() {
//    companion object {
//        lateinit var database: AppDatabase
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        database = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java,
//            "employee_db"
//        ).build()
//    }
//}