package com.jmyze.gradecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoursesViewModel : ViewModel() {
    private val courseObject: CourseObject = CourseObject()
    private var _course = MutableLiveData(courseObject)
    val course: LiveData<CourseObject> = _course

    fun saveCourse(newCourse: CourseObject) {
        _course.value = newCourse
    }
}