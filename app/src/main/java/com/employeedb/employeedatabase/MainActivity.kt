package com.employeedb.employeedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.employeedb.employeedatabase.navigation.AppNavGraph
import com.employeedb.employeedatabase.ui.theme.EmployeeDatabaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmployeeDatabaseTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}