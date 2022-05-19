package com.jmyze.gradecalculator

import androidx.annotation.WorkerThread
import com.jmyze.gradecalculator.database.CourseDatabaseDao
import com.jmyze.gradecalculator.database.CourseObject

class CourseRepository(private val courseDao: CourseDatabaseDao) {
    @WorkerThread
    suspend fun insert(course: CourseObject) {
        courseDao.insert(course)
    }

    @WorkerThread
    suspend fun delete(course: CourseObject) {
        courseDao.delete(course)
    }

    fun getAllItems() = courseDao.getAllCourses()
}