package com.example.rajiv.mvvmproject.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.rajiv.mvvmproject.R
import com.example.rajiv.mvvmproject.adapter.EmployeeAdapter
import com.example.rajiv.mvvmproject.model.Employee
import com.example.rajiv.mvvmproject.repo.AppRepository
import com.example.rajiv.mvvmproject.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), EmployeeAdapter.EmployeeAdapterCallback {

    lateinit var mainViewModel: MainViewModel
    private var employeeAdapter: EmployeeAdapter? = null
    private val dataList = mutableListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intiRecyclerView()
        initViewModel()

    }

    private fun intiRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel() {

        val employeesObserver: Observer<List<Employee>> = Observer<List<Employee>> { data ->

            dataList.clear()
            dataList.addAll(data!!)

            if (employeeAdapter == null) {
                employeeAdapter = EmployeeAdapter(dataList)
                employeeAdapter?.callback = this@MainActivity
                recyclerView.adapter = employeeAdapter
            } else {
                employeeAdapter?.notifyDataSetChanged()

            }

        }

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.mEmployees.observe(this, employeesObserver)

    }

    override fun delete(position: Int) {
        mainViewModel.delete(position)
        employeeAdapter?.notifyItemRemoved(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppRepository.destroyInstance()
    }
}
