package com.example.rajiv.mvvmproject.repo.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.rajiv.mvvmproject.model.Employee

@Dao
interface EmployeeDao {

    @Insert
    fun insertAll(employees: List<Employee>)

    @Query("SELECT * FROM employee")
    fun getAllEmployee(): LiveData<List<Employee>>

    @Delete
    fun deleteEmployee(employee: Employee)

    @Query("SELECT COUNT(*) FROM employee")
    fun getCount(): Int

}