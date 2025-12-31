package com.employeedb.employeedatabase.ui.components.employees

data class EmployeeFormState(
    val name: String = "",
    val email: String = "",
    val role : String = "",
    val department : String = "",
    val joinDate: String = "",

    val isLoading: Boolean = false,
    val error: String? = null
)