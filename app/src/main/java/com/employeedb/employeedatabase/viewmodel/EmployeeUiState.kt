package com.employeedb.employeedatabase.viewmodel

import com.employeedb.employeedatabase.model.Employee

data class EmployeeUiState(
    val isLoading: Boolean = false,
    val employees: List<Employee> = emptyList(),
    val filteredEmployee: List<Employee> = emptyList(),
    val searchQuery: String = "",
    val selectedDepartment: String? = null,
    val error: String? = null
)