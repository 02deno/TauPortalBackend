package com.example.tau.demo.controller

import com.example.tau.demo.model.Lesson
import com.example.tau.demo.service.LessonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/lessons")

class LessonController(private val service: LessonService) {

    // to handle exception if the given ID does not exist
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getLessons() : Collection<Lesson> = service.getLessons()

    @GetMapping("/{name}")
    fun getLesson(@PathVariable name : String) = service.getLesson(name)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addLesson(@RequestBody lesson: Lesson) : Lesson = service.addLesson(lesson)

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteLesson(@PathVariable name : String) : Unit = service.deleteLesson(name)

    @PatchMapping
    fun updateLesson(@RequestBody lesson: Lesson): Lesson = service.updateLesson(lesson)
}