package com.example.rajiv.kotlinworld.api

import com.example.rajiv.mvvmproject.model.Employee
import retrofit2.Call
import retrofit2.http.GET

interface EmployeeApi {
    @GET("employees")
    fun getEmployees(): Call<List<Employee>>
}