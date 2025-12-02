package com.employeedb.employeedatabase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.employeedb.employeedatabase.model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmp(emp: Employee)

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): Flow<List<Employee>>

    @Query("SELECT * FROM employees where id = :id")
    suspend fun getEmpById(id: Int): Employee?

    @Update
    suspend fun updateEmp(emp: Employee)

    @Delete
    suspend fun deleteEmp(emp: Employee)

    @Query("DELETE from employees where id =:id")
    suspend fun deleteEmpById(id: Int): Int?
}