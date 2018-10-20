package com.example.rajiv.kotlinworld.api

import com.example.rajiv.mvvmproject.model.Employee
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmployeeRetriever {
    private val service: EmployeeApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(EmployeeApi::class.java)
    }

    fun getEmployees(callback: Callback<List<Employee>>) {
        val call = service.getEmployees()
        call.enqueue(callback)
    }
}