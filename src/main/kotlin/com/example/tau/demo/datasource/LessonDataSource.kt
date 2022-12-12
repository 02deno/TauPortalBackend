package com.example.tau.demo.datasource

import com.example.tau.demo.model.Lesson

interface LessonDataSource {
    fun retrieveLessons() : Collection<Lesson>
    fun retrieveLesson(name : String): Lesson
    fun createLesson(lesson: Lesson): Lesson
    fun deleteLesson(name : String)
    fun updateLesson(lesson: Lesson): Lesson
}