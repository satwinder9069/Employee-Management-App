package com.employeedb.employeedatabase.data.repository

import com.employeedb.employeedatabase.data.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {
    fun getEmployees(): Flow<List<Employee>>

    fun getEmployeeById(id: Long) : Flow<Employee?>

    suspend fun addEmployee(employee: Employee)

    suspend fun updateEmployee(employee: Employee)

    suspend fun deleteEmployee(employee: Employee)

    fun searchAndFilter(query: String, dept: String): Flow<List<Employee>>


}