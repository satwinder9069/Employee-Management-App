package com.employeedb.employeedatabase.ui.components.attendance

import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.employeedb.employeedatabase.model.AttendanceStatus
import com.employeedb.employeedatabase.model.Employee
import com.employeedb.employeedatabase.viewmodel.AttendanceViewModel
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkAttendanceDialog(
    employees: List<Employee>,
    selectedDate: Long,
    markedEmployeeIds: List<Long>,
    viewModel: AttendanceViewModel,
    onDismiss: () -> Unit,
    onSave: (Long, String?, String?, AttendanceStatus) -> Unit
) {
    var selectedEmployee by remember { mutableStateOf<Employee?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var inTime by remember { mutableStateOf("9:00") }
    var outTime by remember { mutableStateOf("18:00") }
    var selectedStatus by remember { mutableStateOf(AttendanceStatus.PRESENT) }

    val availableEmployees = employees.filter { it.id !in markedEmployeeIds }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Mark Attendance") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedEmployee?.name ?: "Selected Employee",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Employee")},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier.fillMaxWidth().menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        if (availableEmployees.isEmpty()) {
                            DropdownMenuItem(
                                text = { Text("All employees marked") },
                                onClick = { }
                            )
                        } else {
                            availableEmployees.forEach { employee ->
                                DropdownMenuItem(
                                    text = { Text(employee.name) },
                                    onClick = {
                                        selectedEmployee = employee
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                // In Time
                OutlinedTextField(
                    value = inTime,
                    onValueChange = { inTime = it },
                    label = { Text("In Time (HH:MM)") },
                    placeholder = { Text("09:00") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Out Time
                OutlinedTextField(
                    value = outTime,
                    onValueChange = { outTime = it },
                    label = { Text("Out Time (HH:MM)") },
                    placeholder = { Text("18:00") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Status Selection
                Text(
                    text = "Status",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AttendanceStatus.values().forEach { status ->
                        FilterChip(
                            selected = selectedStatus == status,
                            onClick = { selectedStatus = status },
                            label = { Text(status.name) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    selectedEmployee?.let { employee->
                        onSave(
                            employee.id,
                            if (selectedStatus == AttendanceStatus.LEAVE) null else inTime,
                            if (selectedStatus == AttendanceStatus.LEAVE) null else outTime,
                            selectedStatus
                        )
                    }
                },
                enabled = selectedEmployee != null
            ) {
                Text("Save")
            }

        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}