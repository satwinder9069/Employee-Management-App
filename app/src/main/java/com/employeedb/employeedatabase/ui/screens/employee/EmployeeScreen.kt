package com.employeedb.employeedatabase.ui.screens.employee

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.employeedb.employeedatabase.model.Employee
import com.employeedb.employeedatabase.navigation.Screen
import com.employeedb.employeedatabase.ui.components.common.BottomNavigationBar
import com.employeedb.employeedatabase.ui.components.employees.EmployeeCountWithFilter
import com.employeedb.employeedatabase.ui.components.employees.EmployeeItem
import com.employeedb.employeedatabase.ui.components.employees.EmployeesHeader
import com.employeedb.employeedatabase.ui.components.employees.FilterTabs
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeScreen(
    viewModel: EmployeeViewModel = hiltViewModel(),
    onAddClick: () -> Unit,
    navController: NavHostController
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.EmployeeForm.createRoute()) },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Employee"
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) { paddingValues ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Text(
                    text = state.error!!,
                    modifier = Modifier.padding(16.dp)
                )
            }

            state.employees.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No employees found")
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    EmployeesHeader(
                        searchQuery = state.searchQuery,
                        onSearchChange = viewModel::onSearchQueryChange
                    )
                    FilterTabs(
                        selectedTab = state.selectedDepartment ?: "All",
                        onTabSelected = viewModel::onDepartmentSelected
                    )

                    EmployeeCountWithFilter(
                        count = state.filteredEmployee.size,
                    )

                    EmployeeList(
                        state.filteredEmployee,
                        modifier = Modifier.weight(1f),
                        navController = navController
                    )

                }
            }
        }
    }
}

@Composable
fun EmployeeList(
    employees: List<Employee>,
    modifier: Modifier,
    navController: NavHostController
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = employees,
            key = { it.id }
        ) { employee ->
            EmployeeItem(employee, navController)
        }
    }
}