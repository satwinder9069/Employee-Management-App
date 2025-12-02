package com.employeedb.employeedatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.employeedb.employeedatabase.data.repository.EmployeeRepository
import com.employeedb.employeedatabase.model.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repo : EmployeeRepository
) : ViewModel() {

    private val _employees = MutableStateFlow<List<Employee>>(emptyList())
    val employees: StateFlow<List<Employee>> = _employees

    private val _selectedEmployee = MutableStateFlow<Employee?>(null)
    val selectedEmployee: StateFlow<Employee?> = _selectedEmployee

    init {
        getAllEmployees()
    }

    fun getAllEmployees() {
        viewModelScope.launch {
            repo.getEmployees().collect { list ->
                _employees.value = list
            }
        }
    }

    fun getEmpById(id: Int) {
        viewModelScope.launch {
            val emp = repo.getEmpById(id)
            _selectedEmployee.value  = emp
        }
    }
    fun addEmp(emp: Employee) {
        viewModelScope.launch {
            repo.addEmp(emp)
        }
    }

    fun updateEmp(emp: Employee) {
        viewModelScope.launch {
            repo.updateEmp(emp)
        }
    }

    fun deleteEmp(emp: Employee) {
        viewModelScope.launch {
            repo.deleteEmp(emp)
        }
    }

}