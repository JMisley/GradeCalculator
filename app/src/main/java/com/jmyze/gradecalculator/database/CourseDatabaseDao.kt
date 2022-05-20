package com.jmyze.gradecalculator.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CourseDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courseObject: CourseObject)

    @Update
    suspend fun update(courseObject: CourseObject)

    @Delete
    suspend fun delete(courseObject: CourseObject)

    @Query("SELECT * FROM course_table")
    fun getAllCourses() : LiveData<List<CourseObject>>
}