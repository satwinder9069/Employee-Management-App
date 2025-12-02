package com.employeedb.employeedatabase.data.repository

import com.employeedb.employeedatabase.data.local.EmployeeDao
import com.employeedb.employeedatabase.model.Employee
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val dao: EmployeeDao
) {
    fun getEmployees(): Flow<List<Employee>> = dao.getAllEmployees()

    suspend fun addEmp(emp: Employee) {
        dao.addEmp(emp)
    }

    suspend fun getEmpById(id: Int): Employee? {
        return dao.getEmpById(id)
    }

    suspend fun updateEmp(emp: Employee) {
        dao.updateEmp(emp)
    }

    suspend fun deleteEmp(emp: Employee) {
        dao.deleteEmp(emp)
    }

    suspend fun deleteEmpById(id: Int): Int? {
        return dao.deleteEmpById(id)
    }

}