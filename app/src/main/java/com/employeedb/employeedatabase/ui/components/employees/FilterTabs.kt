package com.employeedb.employeedatabase.ui.components.employees

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.employeedb.employeedatabase.viewmodel.EmployeeViewModel

@Composable
fun FilterTabs(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    viewModel: EmployeeViewModel = hiltViewModel()
) {

    val allEmployees by viewModel.uiState.collectAsState()

    // Get unique departments from actual employees
    val departments = remember(allEmployees.employees) {
        listOf("All") + allEmployees.employees
            .map { it.department.trim() }
            .distinct()
            .sorted()
    }

    val selectedIndex = departments.indexOf(selectedTab).coerceAtLeast(0)

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        edgePadding = 16.dp
    ) {
        departments.forEach { title ->
            Tab(
                selected = title == selectedTab,
                onClick = { onTabSelected(title) },
                text = {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            )
        }
    }
}