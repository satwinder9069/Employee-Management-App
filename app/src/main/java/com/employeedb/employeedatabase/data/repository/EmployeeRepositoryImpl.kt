package com.employeedb.employeedatabase.data.repository

import com.employeedb.employeedatabase.data.local.EmployeeDao
import com.employeedb.employeedatabase.data.model.Employee
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val employeeDao : EmployeeDao
): EmployeeRepository {
    override fun getEmployees(): Flow<List<Employee>> = employeeDao.getAllEmployees()

    override fun getEmployeeById(id: Long) : Flow<Employee?> = employeeDao.getEmpById(id)

    override suspend fun addEmployee(emp: Employee) {
        employeeDao.addEmp(emp)
    }

    override suspend fun updateEmployee(emp: Employee) {
        employeeDao.updateEmp(emp)
    }

    override suspend fun deleteEmployee(emp: Employee) {
        employeeDao.deleteEmp(emp)
    }

    override fun searchAndFilter(query: String, dept: String): Flow<List<Employee>> = employeeDao.searchAndFilterEmp(query, dept)

    override suspend fun isEmailDuplicate(email: String, excludeId: Long): Boolean {
        return employeeDao.isEmailExists(email, excludeId) > 0
    }
}