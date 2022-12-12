package com.example.tau.demo.service

import com.example.tau.demo.datasource.StudentDataSource
import com.example.tau.demo.datasource.UserDataSource
import com.example.tau.demo.model.Student
import com.example.tau.demo.model.User
import org.springframework.stereotype.Service

@Service
class StudentService(private val dataSource : StudentDataSource) {
    fun getStudents(): Collection<Student> = dataSource.retrieveStudents()
    fun getStudent(mail: String): Student = dataSource.retrieveStudent(mail)
    fun addStudent(student:Student): Student = dataSource.createStudent(student)
    fun deleteStudent(mail: String) = dataSource.deleteStudent(mail)
    fun updateStudent(student:Student): Student = dataSource.updateStudent(student)
}