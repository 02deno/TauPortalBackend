package com.example.tau.demo.service

import com.example.tau.demo.datasource.LessonDataSource
import com.example.tau.demo.model.Lesson
import org.springframework.stereotype.Service

@Service
class LessonService(private val dataSource : LessonDataSource) {
    fun getLessons(): Collection<Lesson> = dataSource.retrieveLessons()
    fun getLesson(name : String): Lesson = dataSource.retrieveLesson(name)
    fun addLesson(lesson: Lesson): Lesson = dataSource.createLesson(lesson)
    fun deleteLesson(name : String) = dataSource.deleteLesson(name)
    fun updateLesson(lesson: Lesson): Lesson = dataSource.updateLesson(lesson)
}