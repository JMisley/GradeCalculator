package com.jmyze.gradecalculator.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CourseDatabaseDao {
    @Insert
    fun insert(courseObject: CourseObject)

    @Update
    fun update(courseObject: CourseObject)

    @Delete
    fun delete(courseObject: CourseObject)

    @Query("SELECT * FROM course_table WHERE courseId = :key")
    fun get(key: Long): CourseObject?

    @Query("SELECT * FROM course_table")
    fun getAllCourses() : LiveData<List<CourseObject>>
}