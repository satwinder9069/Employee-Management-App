package com.employeedb.employeedatabase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.employeedb.employeedatabase.model.Employee
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmployeeScreen(
    viewModel: EmployeeViewModel,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Employee") },
                navigationIcon = {
                    IconButton(
                        onClick = {onBack()}
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()

            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = department,
                onValueChange = { department = it },
                label = { Text("Department") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = salary,
                onValueChange = { salary = it },
                label = { Text("Salary") },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.addEmp(
                        Employee(
                            id = 0,
                            name = name,
                            email = email,
                            salary = salary.toDoubleOrNull() ?: 0.0,
                            department = department
                        )
                    )
                    onBack()
                },
                modifier = Modifier.fillMaxWidth().height(52.dp).width(100.dp)
            ) {
                Text("Save Employee")
            }

        }
    }
}

