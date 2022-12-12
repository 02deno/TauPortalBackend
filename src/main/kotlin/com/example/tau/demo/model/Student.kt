package com.example.tau.demo.model

data class Student(
    val studentNumber: String,
    val depermant: String,
    var semester: Int,
    val lectures: Lesson
)