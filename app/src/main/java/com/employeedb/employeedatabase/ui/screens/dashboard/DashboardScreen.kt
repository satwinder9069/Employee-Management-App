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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.employeedb.employeedatabase.ui.components.common.BottomNavigationBar
import com.employeedb.employeedatabase.ui.components.dashboard.AttendanceOverview
import com.employeedb.employeedatabase.ui.components.dashboard.HeaderSection
import com.employeedb.employeedatabase.ui.components.dashboard.RecentActivities
import com.employeedb.employeedatabase.ui.components.dashboard.StatsSection
import com.employeedb.employeedatabase.ui.components.dashboard.UpcomingLeaves
import com.employeedb.employeedatabase.viewmodel.DashboardViewModel
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: EmployeeViewModel,
    navController: NavHostController,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    onSignOut: () -> Unit = {}
) {

    // Collect all states
    val totalEmployees by dashboardViewModel.totalEmployee.collectAsState()
    val presentToday by dashboardViewModel.presentToday.collectAsState()
    val onLeaveToday by dashboardViewModel.onLeaveToday.collectAsState()
    val lateToday by dashboardViewModel.lateToday.collectAsState()
    val weekAttendance by dashboardViewModel.weekAttendancePercentage.collectAsState()
    val recentActivities by dashboardViewModel.recentActivities.collectAsState()

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
                    totalEmployees = totalEmployees,
                    presentToday = presentToday,
                    onLeave = onLeaveToday,
                    lateArrivals = lateToday,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 100.dp)
                        .zIndex(1f)
                )
            }
            Spacer(modifier = Modifier.height(110.dp))
            AttendanceOverview(
                weekProgress = weekAttendance,
                monthProgress = weekAttendance * 0.95f
            )
            RecentActivities(
                activities = recentActivities
            )
//            UpcomingLeaves()
        }
    }
}