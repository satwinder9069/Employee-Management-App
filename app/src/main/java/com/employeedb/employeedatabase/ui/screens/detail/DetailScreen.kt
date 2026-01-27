package com.employeedb.employeedatabase.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.employeedb.employeedatabase.R
import com.employeedb.employeedatabase.model.Attendance
import com.employeedb.employeedatabase.model.AttendanceStatus
import com.employeedb.employeedatabase.model.Employee
import com.employeedb.employeedatabase.navigation.Screen
import com.employeedb.employeedatabase.ui.components.details.ContactInfo
import com.employeedb.employeedatabase.ui.components.details.EmployeeDetailHeader
import com.employeedb.employeedatabase.ui.components.details.EmploymentDetails
import com.employeedb.employeedatabase.ui.components.details.InfoCardRow
import com.employeedb.employeedatabase.ui.components.details.RecentAttendanceSection
import com.employeedb.employeedatabase.ui.utils.formatDate
import com.employeedb.employeedatabase.viewmodel.AttendanceViewModel
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    employeeId: Long,
    empViewModel: EmployeeViewModel = hiltViewModel(),
    attendanceViewModel: AttendanceViewModel = hiltViewModel()
) {
    val employee by empViewModel.getEmpById(employeeId).collectAsState()
    val attendance by attendanceViewModel.getRecentAttendance(employeeId).collectAsState()

    if (employee == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()

        }
        return
    }

    DetailContent(
        navController = navController,
        employee = employee!!,
        attendance = attendance
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailContent(
    navController: NavHostController,
    employee: Employee,
    attendance: List<Attendance>
) {
    val attendancePercent by remember {
        derivedStateOf {
            if (attendance.isEmpty()) "0%"
            else {
                val presentCount = attendance.count { it.status == AttendanceStatus.PRESENT }
                val percentage = (presentCount.toFloat() / attendance.size * 100).toInt()
                "$percentage%"
            }
        }
    }

    val leavesTaken = remember {
        attendance.count { it.status == AttendanceStatus.LEAVE }.toString()
    }

    val totalWorkingDays = remember {
        attendance.size.toString()
    }

    var showComingSoon by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Details",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E5EFF)
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item ("header") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                ) {
                    EmployeeDetailHeader(
                        initials = employee.initials,
                        name = employee.name,
                        role = employee.role,
                        department = employee.department,
                        modifier = Modifier.height(130.dp)
                    )
                    InfoCardRow(
                        attendancePercent = attendancePercent,
                        leavesTaken = leavesTaken,
                        totalDays = totalWorkingDays,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    )
                }
            }
            item {
                ContactInfo(email = employee.email)
            }
            item {
                EmploymentDetails(
                    employeeId = employee.id.toString(),
                    joinDate = formatDate(employee.joinDate),
                    department = employee.department
                )
            }
            item {
                RecentAttendanceSection(attendance = attendance)
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            navController.navigate(
                                Screen.EmployeeForm.createRoute(employee.id)
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text("Edit Details")
                    }

                    OutlinedButton(
                        onClick = { showComingSoon = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text("Documents")
                    }

                }
            }
        }
    }
    if (showComingSoon) {
        AlertDialog(
            onDismissRequest = { showComingSoon = false },
            title = { Text("Coming Soon") },
            text = { Text("Document management feature will be available soon.") },
            confirmButton = {
                TextButton(onClick = { showComingSoon = false }) {
                    Text("OK")
                }
            }
        )
    }
}
