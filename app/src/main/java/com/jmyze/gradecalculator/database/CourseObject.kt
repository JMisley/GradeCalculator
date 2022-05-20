package com.jmyze.gradecalculator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class CourseObject(
    @ColumnInfo(name = "course_name")
    var courseName: String = "",

    @ColumnInfo(name = "color_id")
    var colorID: Int = 0,

    @ColumnInfo(name = "overall_grade")
    var gradePercentage: Double = 0.0,

    @ColumnInfo(name = "instructor")
    var instructor: String = "",

    @ColumnInfo(name = "course_code")
    var courseCode: String = "",
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

class Grade {
    var assignment: String = ""
    var grade: Double = 0.0

    constructor(assignment: String, grade: Double) {
        this.assignment = assignment
        this.grade = grade
    }
}