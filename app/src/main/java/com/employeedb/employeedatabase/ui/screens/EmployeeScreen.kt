package com.employeedb.employeedatabase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.employeedb.employeedatabase.model.Employee
import com.employeedb.employeedatabase.ui.components.EmployeeCard
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeScreen(
    viewModel : EmployeeViewModel,
    onAddClick : () -> Unit,
    onEditClick: (Employee) -> Unit
) {
    val employees = viewModel.employees.collectAsState().value
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Employees",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Employee"
                )
            }
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(employees) {emp ->
                EmployeeCard(
                    employee = emp,
                    onEdit = { onEditClick(emp) },
                    onDelete = {
                        scope.launch {
                            viewModel.deleteEmp(emp)
                        }
                    }
                )
            }
        }
    }
}