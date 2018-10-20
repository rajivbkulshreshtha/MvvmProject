package com.example.rajiv.mvvmproject.repo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.rajiv.mvvmproject.model.Employee

@Database(entities = arrayOf(Employee::class), version = 1)
abstract class EmployeeDB : RoomDatabase() {

    companion object {
        val LOCK = Object()

        private var INSTANCE: EmployeeDB? = null

        fun getInstance(context: Context): EmployeeDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            EmployeeDB::class.java, "sample.db").build()
                }
            }

            return INSTANCE!!

        }
    }

    abstract fun employeeDao(): EmployeeDao

}