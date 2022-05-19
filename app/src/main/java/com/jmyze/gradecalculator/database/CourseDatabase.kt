package com.jmyze.gradecalculator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CourseObject::class], version = 1, exportSchema = false)
abstract class CourseDatabase : RoomDatabase() {
    abstract val courseDatabaseDao: CourseDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: CourseDatabase? = null
        fun getInstance(context: Context): CourseDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CourseDatabase::class.java,
                        "course_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}