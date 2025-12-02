package com.employeedb.employeedatabase.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.employeedb.employeedatabase.ui.screens.AddEmployeeScreen
import com.employeedb.employeedatabase.ui.screens.EmployeeScreen
import com.employeedb.employeedatabase.ui.screens.UpdateEmployeeScreen
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: EmployeeViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.EmployeeListScreen.route
    ) {
        composable(
            route = Screen.EmployeeListScreen.route,
        ) {
            EmployeeScreen(
                viewModel = viewModel,
                onAddClick = {navController.navigate(Screen.AddEmployee.route)},
                onEditClick = {emp->
                    navController.navigate(Screen.UpdateEmployee.createRoute(emp.id))}
            )
        }
        composable(Screen.AddEmployee.route) {
            AddEmployeeScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.UpdateEmployee.route,
            arguments = listOf(navArgument("id") {type = NavType.IntType})
        ) { backStackEntry->
            val employeeId = backStackEntry.arguments?.getInt("id") ?: 0
            UpdateEmployeeScreen(
                viewModel = viewModel,
                empId = employeeId,
                onBack = {navController.popBackStack()}
            )
        }
    }
}