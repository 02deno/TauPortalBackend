package com.example.tau.demo.controller

import com.example.tau.demo.model.Student
import com.example.tau.demo.model.User
import com.example.tau.demo.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/students")
class StudentController(private val service : StudentService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getStudents() : Collection<Student> = service.getStudents()

    @GetMapping("/{mail}")
    fun getStudent(@PathVariable mail: String) = service.getStudent(mail)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody student : Student) : Student = service.addStudent(student)

    @DeleteMapping("/{mail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable mail: String) : Unit = service.deleteStudent(mail)

    @PatchMapping
    fun updateUser(@RequestBody student: Student): Student = service.updateStudent(student)
}