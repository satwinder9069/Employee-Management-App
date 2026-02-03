package com.employeedb.employeedatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.employeedb.employeedatabase.data.repository.EmployeeRepository
import com.employeedb.employeedatabase.data.model.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repo: EmployeeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EmployeeUiState())
    val uiState: StateFlow<EmployeeUiState> = _uiState

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError : StateFlow<String?> = _emailError
    private val employeeCache = mutableMapOf<Long, StateFlow<Employee?>>()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadEmployees()
    }

    sealed class UiEvent() {
        object EmployeeSaved : UiEvent()
        data class ShowError(val message: String): UiEvent()
    }

    private fun loadEmployees() {
        viewModelScope.launch {
            repo.getEmployees()
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }.catch { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }.collect { employees ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            employees = employees,
                            filteredEmployee = applyFilters(
                                employees,
                                state.searchQuery,
                                state.selectedDepartment
                            )
                        )
                    }
                }
        }
    }

    private fun applyFilters(
        employees: List<Employee>,
        query: String,
        department: String?
    ) : List<Employee> {
        return employees
            .filter { query.isBlank() || it.name.contains(query, ignoreCase = true) }
            .filter {
                department == null ||
                        department == "All" ||
                        it.department == department ||
                        it.department.trim().equals(
                            department.trim(),
                            ignoreCase = true
                        )
            }
    }

    fun getEmpById(id: Long): StateFlow<Employee?> {
        return employeeCache.getOrPut(id) {
            repo.getEmployeeById(id)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = null
                )
        }
    }

    fun addEmp(emp: Employee) {
        viewModelScope.launch {
            if  (repo.isEmailDuplicate(emp.email)) {
                _emailError.value = "Email already exists"
                _uiEvent.emit(UiEvent.ShowError("Email already exists"))
                return@launch
            }
            _emailError.value = null
            repo.addEmployee(emp)
        }
    }

    fun updateEmp(emp: Employee) {
        viewModelScope.launch {
            if (repo.isEmailDuplicate(emp.email, emp.id)) {
                _emailError.value = "Email already exists"
                _uiEvent.emit(UiEvent.ShowError("Email already exists"))
                return@launch
            }
            _emailError.value = null
            repo.updateEmployee(emp)
        }
    }

    fun deleteEmp(emp: Employee) {
        viewModelScope.launch {
            repo.deleteEmployee(emp)
        }
    }

    fun clearEmailError() {
        _emailError.value = null
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(searchQuery = query,
                filteredEmployee = applyFilters(
                    it.employees,
                    query,
                    it.selectedDepartment
                )
            )
        }
    }

    fun onDepartmentSelected(department: String?) {
        _uiState.update {
            it.copy(
                selectedDepartment = department ,
                filteredEmployee = applyFilters(
                    it.employees,
                    it.searchQuery,
                    department
                )
            )
        }
    }
}