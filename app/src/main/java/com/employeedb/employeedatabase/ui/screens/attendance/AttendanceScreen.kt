package com.employeedb.employeedatabase.ui.screens.attendance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.employeedb.employeedatabase.ui.components.attendance.AttendanceList
import com.employeedb.employeedatabase.ui.components.attendance.AttendanceSummaryCard
import com.employeedb.employeedatabase.ui.components.attendance.DatePickerDialog
import com.employeedb.employeedatabase.ui.components.attendance.DateSelectorCard
import com.employeedb.employeedatabase.ui.components.attendance.EmptyAttendanceState
import com.employeedb.employeedatabase.ui.components.attendance.MarkAttendanceDialog
import com.employeedb.employeedatabase.ui.components.common.BottomNavigationBar
import com.employeedb.employeedatabase.viewmodel.AttendanceViewModel

@Composable
fun AttendanceScreen(
    navigation: NavHostController,
    viewModel: AttendanceViewModel = hiltViewModel()
) {

    val selectedDate by viewModel.selectedDate.collectAsState()
    val employees by viewModel.employees.collectAsState()
    val attendanceList by viewModel.attendanceForDate.collectAsState()
    val summary by viewModel.attendanceSummary.collectAsState()

    var showDatePicker by remember { mutableStateOf(false) }
    var showMarkDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigation)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showMarkDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Mark Attendance")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // date selector
            DateSelectorCard(
                selectedDate = selectedDate,
                onDateClick = { showDatePicker = true }
            )

            // summary card
            AttendanceSummaryCard(summary = summary)

            // attendance list
            if(attendanceList.isEmpty()) {
                EmptyAttendanceState()
            } else {
                AttendanceList(
                    attendanceList = attendanceList,
                    onDelete = { attendance ->
                        viewModel.deleteAttendance(attendance.attendance)
                    }
                )
            }

        }
    }
    // Date Picker Dialog
    if (showDatePicker) {
        DatePickerDialog(
            currentDate = selectedDate,
            onDateSelected = { date ->
                viewModel.updateSelectedDate(date)
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }
    // Mark Attendance Dialog
    if (showMarkDialog) {
        MarkAttendanceDialog(
            employees = employees,
            selectedDate = selectedDate,
            markedEmployeeIds = attendanceList.map { it.attendance.employeeId },
            viewModel = viewModel,
            onDismiss = { showMarkDialog = false },
            onSave = { employeeId, inTime, outTime, status ->
                viewModel.markAttendance(
                    employeeId = employeeId,
                    inTime = inTime,
                    outTime = outTime,
                    status = status
                )
                showMarkDialog = false
            }
        )
    }
}