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

    @Query("SELECT * FROM employees ORDER BY id ASC")
    fun getAllEmployees(): Flow<List<Employee>>

    @Query("SELECT * FROM employees where id = :id")
    fun getEmpById(id: Long): Flow<Employee?>

    @Update
    suspend fun updateEmp(emp: Employee)

    @Delete
    suspend fun deleteEmp(emp: Employee)

    @Query("DELETE from employees where id =:id")
    suspend fun deleteEmpById(id: Int): Int?

    @Query("SELECT * FROM employees WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchEmployees(query: String):Flow<List<Employee>>

    //getting emp by department
    @Query("SELECT * FROM employees WHERE department = :dept ORDER BY name ASC")
    fun getEmployeeByDept(dept: String): Flow<List<Employee>>

    //search and filter
    @Query("SELECT * FROM employees WHERE name LIKE '%' || :query || '%' AND (:dept = 'ALL' OR department = :dept) ORDER BY name ASC")
    fun searchAndFilterEmp(query: String, dept: String): Flow<List<Employee>>
}