package com.employeedb.employeedatabase.ui.screens.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.employeedb.employeedatabase.ui.components.common.BottomNavigationBar
import com.employeedb.employeedatabase.ui.components.dashboard.AttendanceOverview
import com.employeedb.employeedatabase.ui.components.dashboard.HeaderSection
import com.employeedb.employeedatabase.ui.components.dashboard.RecentActivities
import com.employeedb.employeedatabase.ui.components.dashboard.StatsSection
import com.employeedb.employeedatabase.ui.components.dashboard.UpcomingLeaves
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: EmployeeViewModel,
    navController: NavHostController
) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                HeaderSection(
                    modifier = Modifier
                        .height(150.dp)
                )
                StatsSection(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 100.dp)
                        .zIndex(1f)
                )
            }
            Spacer(modifier = Modifier.height(110.dp))
            AttendanceOverview()
            RecentActivities()
            UpcomingLeaves()
        }
    }
}