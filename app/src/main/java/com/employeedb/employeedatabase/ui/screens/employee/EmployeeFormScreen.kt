package com.employeedb.employeedatabase.ui.screens.employee

import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.employeedb.employeedatabase.R
import com.employeedb.employeedatabase.data.model.Employee
import com.employeedb.employeedatabase.ui.components.employees.EmployeeFormState
import com.employeedb.employeedatabase.ui.utils.formatDate
import com.employeedb.employeedatabase.ui.utils.parseDate
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

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
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    var showDatePicker by remember { mutableStateOf(false) }
    val emailError by viewModel.emailError.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(employee) {
        employee?.let {
            formState = formState.copy(
                name = it.name,
                email = it.email,
                role = it.role,
                department = it.department,
                joinDate = formatDate(it.joinDate)
            )
            datePicker.selectedDateMillis = it.joinDate
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is EmployeeViewModel.UiEvent.ShowError -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(event.message)
                    }
                }
                else -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Name
            OutlinedTextField(
                value = formState.name,
                onValueChange = {
                    formState = formState.copy(name = it, error = null)
                },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email
            OutlinedTextField(
                value = formState.email,
                onValueChange = {
                    formState = formState.copy(email = it ,  error = null)

                    if (emailError != null) viewModel.clearEmailError()
                },
                label = { Text("Email") },
                isError = emailError != null,
                supportingText = if (emailError != null) {
                    {
                        Text(emailError!!, color = MaterialTheme.colorScheme.error)
                    }
                } else null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))
            // Role
            OutlinedTextField(
                value = formState.role,
                onValueChange = {
                    formState = formState.copy(role = it,  error = null)
                },
                label = { Text("Role") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))
            // Department
            OutlinedTextField(
                value = formState.department,
                onValueChange = {
                    formState = formState.copy(department = it,  error = null)
                },
                label = { Text("Department") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

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
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePicker.selectedDateMillis?.let { millis ->
                                formState = formState.copy(
                                    joinDate = formatDate(millis),
                                    error = null
                                )
                            }
                            showDatePicker = false
                        }) {
                            Text(text = "OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePicker)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            formState.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    val error = validateForm(formState)
                    if (error != null) {
                        formState = formState.copy(error = error)
                        return@Button
                    }

                    val employee = Employee(
                        id = employeeId ?: 0L,
                        name = formState.name.trim(),
                        email = formState.email.trim(),
                        role = formState.role.trim(),
                        department = formState.department.trim(),
                        joinDate = parseDate(formState.joinDate)
                    )
                    scope.launch {
                        if (employeeId == null) {
                            viewModel.addEmp(employee)
                        } else {
                            viewModel.updateEmp(employee)
                        }

                        delay(100)

                        if(viewModel.emailError.value == null){
                            navController.popBackStack()
                        }
                    }
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
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

fun validateForm(state: EmployeeFormState): String? {
    return when {
        state.name.isBlank() -> "Name is required"
        state.name.trim().length < 3 -> "Name must be at least 3 characters"
        state.email.isBlank() -> "Email is required"
        !EMAIL_ADDRESS.matcher(state.email).matches() ->
            "Invalid email format"
        state.role.isBlank() -> "Role is required"
        state.department.isBlank() -> "Department is required"
        state.joinDate.isBlank() -> "Join date is required"
        else -> null
    }
}