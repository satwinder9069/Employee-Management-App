package com.employeedb.employeedatabase.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.employeedb.employeedatabase.model.Employee
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateEmployeeScreen(
    viewModel: EmployeeViewModel,
    empId: Int,
    onBack: () -> Unit
) {
    val employee = viewModel.selectedEmployee.collectAsState().value

    LaunchedEffect(empId) {
        viewModel.getEmpById(empId)
    }

    if (employee == null) {
        Text("Loading..." , modifier = Modifier.padding(16.dp))
        return
    }

    var name by remember { mutableStateOf(employee.name) }
    var email by remember { mutableStateOf(employee.email) }
    var department by remember { mutableStateOf(employee.department) }
    var salary by remember { mutableStateOf(employee.salary.toString()) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Update Employee")
                }
            )
        }
    ){padding->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),

                )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = department,
                onValueChange = { department = it },
                label = { Text("Department") },
                modifier = Modifier.fillMaxWidth(),

                )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = salary,
                onValueChange = { salary = it },
                label = { Text("Salary") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.updateEmp(
                        Employee(
                            id = empId,
                            name = name,
                            email = email,
                            department = department,
                            salary = salary?.toDoubleOrNull() ?: 0.0
                        )
                    )
                    onBack()
                },
                modifier = Modifier.fillMaxWidth().height(52.dp)

            ) {
                Text("Update Employee")
            }
        }
    }
}