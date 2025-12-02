package com.employeedb.employeedatabase.navigation

sealed class Screen(
    val route: String,
    val title: String,
) {
    data object EmployeeListScreen : Screen("employee_list","Employees")
    data object AddEmployee : Screen("add_employee", "Add Employee")
    object UpdateEmployee : Screen("update_employee/{id}", "Update Employee") {
        fun createRoute(id: Int): String = "update_employee/$id"
    }
    data object DetailScreen : Screen("detail", "DetailScreen")
}