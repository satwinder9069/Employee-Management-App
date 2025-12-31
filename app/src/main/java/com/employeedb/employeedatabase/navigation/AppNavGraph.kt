package com.employeedb.employeedatabase.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.employeedb.employeedatabase.ui.screens.attendance.AttendanceScreen
import com.employeedb.employeedatabase.ui.screens.dashboard.DashboardScreen
import com.employeedb.employeedatabase.ui.screens.detail.DetailScreen
import com.employeedb.employeedatabase.ui.screens.employee.EmployeeFormScreen
import com.employeedb.employeedatabase.ui.screens.employee.EmployeeScreen
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: EmployeeViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DashboardScreen.route
    ) {
        composable(
            route = Screen.DashboardScreen.route
        ) {
            DashboardScreen(viewModel, navController = navController)
        }

        composable(
            route = Screen.EmployeeListScreen.route,
        ) {
            EmployeeScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate(Screen.EmployeeForm.route) },
                navController
            )
        }

        composable(
            route = Screen.EmployeeForm.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val employeeId =
                backStackEntry.arguments
                    ?.getString("id")
                    ?.toLong()

            EmployeeFormScreen(
                navController = navController,
                employeeId = employeeId
            )
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backstackEntry ->
            val employeeId = backstackEntry.arguments?.getLong("id") ?: return@composable
            DetailScreen(navController = navController, employeeId = employeeId)
        }

        composable(
            route = Screen.AttendanceScreen.route
        ) {
            AttendanceScreen(navController)
        }

        composable(
            route = Screen.SettingsScreen.route
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Coming Soon")
            }
        }
    }
}