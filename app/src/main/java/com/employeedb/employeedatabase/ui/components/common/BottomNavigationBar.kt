package com.employeedb.employeedatabase.ui.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.employeedb.employeedatabase.R
import com.employeedb.employeedatabase.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {navController.navigate(Screen.DashboardScreen.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Dashboard") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(Screen.EmployeeListScreen.route) },
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Employees") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(Screen.AttendanceScreen.route) },
            icon = { Icon(painterResource(R.drawable.calendar), contentDescription = null) },
            label = { Text("Attendance") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(Screen.SettingsScreen.route)},
            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
            label = { Text("Settings") }
        )
    }
}