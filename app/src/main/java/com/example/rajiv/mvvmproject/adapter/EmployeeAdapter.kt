package com.example.rajiv.mvvmproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.rajiv.mvvmproject.R
import com.example.rajiv.mvvmproject.model.Employee

class EmployeeAdapter(var employees: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    var callback: EmployeeAdapterCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_employee, parent, false))
    }

    override fun getItemCount(): Int {
        return employees.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val employee = employees[position]
        holder.tvName.text = "Name: " + employee.employeeName
        holder.tvSalary.text = "Salary: " + employee.employeeSalary
        holder.tvAge.text = "Age: " + employee.employeeAge

        holder.ivDelete.setOnClickListener { v -> callback?.delete(position) }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName: TextView
        var tvSalary: TextView
        var tvAge: TextView
        var ivDelete: ImageView

        init {

            itemView.tag = this

            tvName = itemView.findViewById(R.id.tvName)
            tvSalary = itemView.findViewById(R.id.tvSalary)
            tvAge = itemView.findViewById(R.id.tvAge)
            ivDelete = itemView.findViewById(R.id.ivDelete)

        }
    }


    interface EmployeeAdapterCallback {
        fun delete(position: Int)
    }

}