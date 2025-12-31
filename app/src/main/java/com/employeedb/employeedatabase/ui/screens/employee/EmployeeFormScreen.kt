package com.employeedb.employeedatabase.ui.screens.employee

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.employeedb.employeedatabase.R
import com.employeedb.employeedatabase.model.Employee
import com.employeedb.employeedatabase.navigation.Screen
import com.employeedb.employeedatabase.ui.components.employees.EmployeeFormState
import com.employeedb.employeedatabase.ui.utils.formatDate
import com.employeedb.employeedatabase.ui.utils.parseDate
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeFormScreen(
    navController: NavController,
    employeeId: Long? = null,
    viewModel: EmployeeViewModel = hiltViewModel()
) {
    var formState by remember { mutableStateOf(EmployeeFormState()) }
    val employee by remember(employeeId) {
        if (employeeId != null)
            viewModel.getEmpById(employeeId)
        else
            flowOf(null)

    }.collectAsState(initial = null)

    val datePicker = rememberDatePickerState(
            initialSelectedDateMillis = employee?.joinDate
    )

    var showDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(employee) {
        employee?.let {
            formState = formState.copy(
                name = it.name,
                email = it.email,
                role = it.role,
                department = it.department,
                joinDate = formatDate(it.joinDate)
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (employeeId == null)
                            "Add Employee"
                        else "Update Employee"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painterResource(R.drawable.arrow_back), contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = formState.name,
                onValueChange = {
                    formState = formState.copy(name = it)
                },
                label = { Text("Name") }
            )

            OutlinedTextField(
                value = formState.email,
                onValueChange = {
                    formState = formState.copy(email = it)
                },
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = formState.role,
                onValueChange = {
                    formState = formState.copy(role = it)
                },
                label = { Text("Role") }
            )

            OutlinedTextField(
                value = formState.department,
                onValueChange = {
                    formState = formState.copy(department = it)
                },
                label = { Text("Department") }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDatePicker = true
                    }
            ) {
                OutlinedTextField(
                    value = formState.joinDate,
                    onValueChange = {},
                    readOnly = true,
                    enabled = false, // important
                    label = { Text("Join Date") },
                    trailingIcon = {
                        Icon(painterResource(R.drawable.calendar), contentDescription = "Pick date")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if(showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {showDatePicker = false},
                    confirmButton = {
                        TextButton(onClick = {
                            datePicker.selectedDateMillis?.let { millis->
                                formState = formState.copy(
                                    joinDate = formatDate(millis)
                                )
                            }
                            showDatePicker = false
                        }) {
                            Text(text = "OK")
                        }
                    }
                ) {
                    DatePicker(state = datePicker)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val error = validateForm(formState)
                    if (error != null) {
                        formState = formState.copy(error = error)
                        return@Button
                    }

                    val employee = Employee(
                        id = employeeId ?: 0L,
                        name = formState.name,
                        email = formState.email,
                        role = formState.role,
                        department = formState.department,
                        joinDate = parseDate(formState.joinDate)
                    )

                    if (employeeId == null) {
                        viewModel.addEmp(employee)
                    } else {
                        viewModel.updateEmp(employee)
                    }
                    navController.navigate(Screen.EmployeeListScreen.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (employeeId == null)
                        "Save Employee"
                    else
                        "Update Employee"
                )
            }
            formState.error?.let {
                Text(text = it, color = Color.Red)
            }
        }
    }
}

fun validateForm(state: EmployeeFormState): String? {
    return when {
        state.name.isBlank() -> "Name is required"
        state.email.isBlank() -> "Email is required"
        state.department.isBlank() -> "Department is required"
        state.joinDate.isBlank() -> "Join date is required"
        else -> null
    }
}