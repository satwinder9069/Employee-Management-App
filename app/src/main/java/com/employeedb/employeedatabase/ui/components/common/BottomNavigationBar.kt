package com.employeedb.employeedatabase.ui.components.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.employeedb.employeedatabase.R
import com.employeedb.employeedatabase.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 4.dp,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {

        // dashboard
        NavigationBarItem(
            selected = currentRoute == Screen.DashboardScreen.route,
            onClick = {
                if (currentRoute != Screen.DashboardScreen.route) {
                    navController.navigate(Screen.DashboardScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Dashboard") }
        )

        // employees
        NavigationBarItem(
            selected = currentRoute == Screen.EmployeeListScreen.route,
            onClick = {
                if (currentRoute != Screen.EmployeeListScreen.route) {
                    navController.navigate(Screen.EmployeeListScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Employees") }
        )

        // attendance
        NavigationBarItem(
            selected = currentRoute == Screen.AttendanceScreen.route,
            onClick = {
                if (currentRoute != Screen.AttendanceScreen.route) {
                    navController.navigate(Screen.AttendanceScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(painterResource(R.drawable.calendar), contentDescription = null) },
            label = { Text("Attendance") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.SettingsScreen.route,
            onClick = {
                if (currentRoute != Screen.SettingsScreen.route) {
                    navController.navigate(Screen.SettingsScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
            label = { Text("Settings") }
        )
    }
}