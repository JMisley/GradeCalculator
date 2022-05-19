package com.jmyze.gradecalculator

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jmyze.gradecalculator.database.CourseDatabaseDao

class CoursesViewModelFactory(
    private val dataSource: CourseDatabaseDao,
    private val application: Application
) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoursesViewModel::class.java)) {
            return CoursesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}