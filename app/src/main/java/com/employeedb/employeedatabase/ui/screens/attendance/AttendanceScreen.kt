package com.employeedb.employeedatabase.ui.screens.attendance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.employeedb.employeedatabase.ui.components.common.BottomNavigationBar

@Composable
fun AttendanceScreen(navigation: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigation)
        }
    ) {padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Screen has to build")
        }
    }
}