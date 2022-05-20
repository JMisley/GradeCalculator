package com.jmyze.gradecalculator.database

class CourseRepository(private val courseDatabase: CourseDatabase) {
    suspend fun insert(course: CourseObject) = courseDatabase.getCourseDao().insert(course)
    suspend fun delete(course: CourseObject) = courseDatabase.getCourseDao().delete(course)

    fun getAllCourses() = courseDatabase.getCourseDao().getAllCourses()
}