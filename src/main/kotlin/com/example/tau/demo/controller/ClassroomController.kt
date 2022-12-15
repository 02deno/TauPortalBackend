package com.example.tau.demo.controller

import com.example.tau.demo.model.Classroom
import com.example.tau.demo.service.ClassroomService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/classrooms")

class ClassroomController(private val service: ClassroomService) {

    // to handle exception if the given ID does not exist
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getClassrooms() : Collection<Classroom> = service.getClassrooms()

    @GetMapping("/{code}")
    fun getClassroom(@PathVariable code : UUID) = service.getClassroom(code)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addClassroom(@RequestBody classroom: Classroom) : Classroom = service.addClassroom(classroom)

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClassroom(@PathVariable code : UUID) : Unit = service.deleteClassroom(code)

    @PatchMapping
    fun updateClassroom(@RequestBody classroom: Classroom): Classroom = service.updateClassroom(classroom)
}