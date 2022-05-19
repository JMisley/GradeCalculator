package com.jmyze.gradecalculator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class CourseObject(
    @PrimaryKey(autoGenerate = true)
    var courseId: Long = 0L,

    @ColumnInfo(name = "color_id")
    var colorID: Int = 0,

    @ColumnInfo(name = "course_name")
    var courseName: String = "",

    @ColumnInfo(name = "grade")
    var gradePercentage: Double = 0.0,

    @ColumnInfo(name = "instructor")
    var instructor: String = "",

    @ColumnInfo(name = "course_code")
    var courseCode: String = ""
) {
    var gradeCategories: ArrayList<String> = arrayListOf()
    var categoryWeight: ArrayList<Double> = arrayListOf()

    var grades: HashMap<String, ArrayList<Grade>> = HashMap()

    private fun getCategoryAverage(category: String): Double {
        var total = 0.0
        if (grades.containsKey(category)) {
            for (i in 0 until grades[category]?.size!!)
                total += grades[category]?.get(i)?.grade!!
            return total / grades[category]?.size!!
        }
        return total
    }

    fun getClassAverage(): Double {
        var total = 0.0
        for (i in 0 until gradeCategories.size)
            total += getCategoryAverage(gradeCategories[i]) * categoryWeight[i]
        return total
    }
}

class Grade {
    var assignment: String = ""
    var grade: Double = 0.0

    constructor(assignment: String, grade: Double) {
        this.assignment = assignment
        this.grade = grade
    }
}