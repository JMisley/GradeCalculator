package com.jmyze.gradecalculator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CourseObject::class], version = 3)
abstract class CourseDatabase : RoomDatabase() {

    abstract fun getCourseDao(): CourseDatabaseDao

    companion object {
        @Volatile
        private var instance: CourseDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CourseDatabase::class.java,
                "Course.db"
            ).build()
    }
}