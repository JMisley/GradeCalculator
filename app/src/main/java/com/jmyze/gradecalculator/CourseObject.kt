package com.jmyze.gradecalculator

class CourseObject {
    var colorID: Int = 0
    var courseName: String = ""
    var gradePercentage: Double = 0.0
    var instructor: String = ""
    var courseCode: String = ""

    var gradeCategories: ArrayList<String> = arrayListOf()
    var categoryWeight: ArrayList<Double> = arrayListOf()

    constructor()

    constructor(
        colorId: Int,
        courseName: String,
        gradePercentage: Double,
        instructor: String,
        courseCode: String
    ) {
        this.colorID = colorId
        this.courseName = courseName
        this.gradePercentage = gradePercentage
        this.courseCode = courseCode.uppercase()
        this.instructor = instructor
    }
}