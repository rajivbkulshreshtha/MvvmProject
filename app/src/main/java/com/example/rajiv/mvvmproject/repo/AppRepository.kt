package com.example.rajiv.mvvmproject.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.rajiv.mvvmproject.model.Employee
import com.example.rajiv.mvvmproject.repo.database.EmployeeDB
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import android.net.ConnectivityManager
import android.util.Log
import com.example.rajiv.kotlinworld.api.EmployeeRetriever
import retrofit2.Call
import retrofit2.Response


class AppRepository(val context: Context) {

    private val mEmployeeDB: EmployeeDB = EmployeeDB.getInstance(context)
    private val mExecutor: Executor = Executors.newSingleThreadExecutor();
    private val mRetriever: EmployeeRetriever = EmployeeRetriever()
    val mEmployees: LiveData<List<Employee>>


    init {
        mEmployees = getAllData()!!
    }

    companion object {

        var INSTANCE: AppRepository? = null

        fun getInstance(context: Context): AppRepository {

            if (INSTANCE == null) {
                INSTANCE = AppRepository(context)
            }

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }


    private fun getAllData(): LiveData<List<Employee>>? {

        if (isOnline(context)) {

            val mutableLiveData: MutableLiveData<List<Employee>> = MutableLiveData()

            val callback = object : retrofit2.Callback<List<Employee>> {

                override fun onFailure(call: Call<List<Employee>>?, t: Throwable?) {
                    Log.e(context::class.java.simpleName, "Problem calling Api")
                }

                override fun onResponse(call: Call<List<Employee>>?, response: Response<List<Employee>>?) {
                    response?.isSuccessful.let {
                        mutableLiveData.value = response?.body()
                        insertAllEmployees(mutableLiveData.value!!)
                    }
                }
            }

            mRetriever.getEmployees(callback)

            return mutableLiveData

        } else {
            return getAllEmployees()
        }

    }


    private fun getAllEmployees(): LiveData<List<Employee>> {
        return mEmployeeDB.employeeDao().getAllEmployee();
    }

    fun insertAllEmployees(employees: List<Employee>) {

        mExecutor.execute { mEmployeeDB.employeeDao().insertAll(employees) }

    }

    fun deleteEmployee(employee: Employee) {
        mExecutor.execute { mEmployeeDB.employeeDao().deleteEmployee(employee) }
    }

    fun getTotalEmployeesCount() {
        mEmployeeDB.employeeDao().getCount()
    }

    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val netInfo = cm!!.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }


}

