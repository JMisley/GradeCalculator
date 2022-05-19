package com.jmyze.gradecalculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmyze.gradecalculator.database.CourseDatabase
import com.jmyze.gradecalculator.database.CourseDatabaseDao
import com.jmyze.gradecalculator.database.CourseObject

//class CoursesViewModel : ViewModel() {
//    private val courseObject: CourseObject = CourseObject()
//    private var _course = MutableLiveData(courseObject)
//    val course: LiveData<CourseObject> = _course
//
//    fun saveCourse(newCourse: CourseObject) {
//        _course.value = newCourse
//    }
//}

class CoursesViewModel(val database: CourseDatabaseDao, application: Application) :
    AndroidViewModel(application) {

}