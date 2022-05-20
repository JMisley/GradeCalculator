package com.jmyze.gradecalculator.database

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CoursesViewModel(private val repository: CourseRepository) : ViewModel() {
    @OptIn(DelicateCoroutinesApi::class)
    fun insert(items: CourseObject) = GlobalScope.launch {
        repository.insert(items)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun delete(items: CourseObject) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllCourses() = repository.getAllCourses()
}