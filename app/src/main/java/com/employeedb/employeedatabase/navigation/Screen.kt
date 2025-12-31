package com.employeedb.employeedatabase.navigation

sealed class Screen(
    val route: String
) {
    data object DashboardScreen : Screen("dashboard")

    data object EmployeeListScreen : Screen("employee_list")

    data object EmployeeForm : Screen("employee_form?id={id}") {
        fun createRoute(id: Long? = null): String =
            if (id == null) {
                "employee_form"
            } else {
                "employee_form?id=$id"
            }
    }

    data object DetailScreen : Screen("detail/{id}") {
        fun createRoute(id: Long): String = "detail/$id"
    }

    data object AttendanceScreen : Screen("attendance")

    data object SettingsScreen: Screen("settings")
}