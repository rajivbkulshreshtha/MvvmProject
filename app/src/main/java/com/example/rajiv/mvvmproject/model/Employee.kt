package com.example.rajiv.mvvmproject.model


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employee")
data class Employee(
        @ColumnInfo(name = "_ID") @PrimaryKey(autoGenerate = true) var _ID: Int, //
        @ColumnInfo(name = "user_id") @SerializedName("id") var id: String?, // 636
        @ColumnInfo(name = "employee_name") @SerializedName("employee_name") var employeeName: String?, // Pedro Alpapa
        @ColumnInfo(name = "employee_salary") @SerializedName("employee_salary") var employeeSalary: String?, // 6000000
        @ColumnInfo(name = "employee_age") @SerializedName("employee_age") var employeeAge: String?, // 27
        @ColumnInfo(name = "profile_image") @SerializedName("profile_image") var profileImage: String?
)


