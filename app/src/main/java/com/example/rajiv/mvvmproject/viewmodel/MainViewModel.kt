package com.example.rajiv.mvvmproject.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.rajiv.mvvmproject.model.Employee
import com.example.rajiv.mvvmproject.repo.AppRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val mEmployees: LiveData<List<Employee>>
    private val mRepository: AppRepository = AppRepository.getInstance(application.applicationContext)

    init {
        mEmployees = mRepository.mEmployees
    }

    fun delete(id:Int){
        mRepository.deleteEmployee(mEmployees.value!![id])
    }

}
