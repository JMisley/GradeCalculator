package com.jmyze.gradecalculator.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoursesViewModelFactory(private val dataSource: CourseRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoursesViewModel::class.java)) {
            return CoursesViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}