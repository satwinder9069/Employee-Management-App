package com.employeedb.employeedatabase.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "employees",
    indices = [
        Index(value = ["email"], unique = true)
    ]
)
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val role: String,
    val department: String,
    val salary: Double? = null,
    val onLeave: Boolean = false,
    val joinDate: Long
) {
    val initials: String
        get() = name.split(" ").mapNotNull {
            it.firstOrNull()?.uppercaseChar()
        }.joinToString("")
}