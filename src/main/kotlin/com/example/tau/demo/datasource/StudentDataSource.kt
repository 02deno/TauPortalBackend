package com.example.tau.demo.datasource

import com.example.tau.demo.model.Student

interface StudentDataSource {
    fun retrieveStudents(): Collection<Student>
    fun retrieveStudent(mail: String): Student
    fun createStudent(student: Student): Student
    fun deleteStudent(mail: String)
    fun updateStudent(student: Student): Student
}