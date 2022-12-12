package com.example.tau.demo.datasource.mock

import com.example.tau.demo.datasource.LessonDataSource
import com.example.tau.demo.model.Classroom
import com.example.tau.demo.model.Lesson
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

@Repository
class MockLessonDataSource : LessonDataSource {
    val lessons = mutableListOf<Lesson>(

        Lesson(
            UUID.randomUUID(),
            "Tıbbı Görüntüleme",
            Classroom(UUID.randomUUID(), "ED-6"),
            LocalDateTime.of(2022, 6, 30, 12, 0,0),
            "Sercan İpek Ugay",
            "Mehmet",
            "nbr"
        ),

        Lesson(
            UUID.randomUUID(),
            "Veri Analizi",
            Classroom(UUID.randomUUID(), "ED-7"),
            LocalDateTime.of(2022, 3, 13, 11, 0,0),
            "Emre Işık",
            "Ali",
            "slm"
        ),

        Lesson(
            UUID.randomUUID(),
            "Yapay Zeka",
            Classroom(UUID.randomUUID(), "ED-5"),
            LocalDateTime.now(),
            "Canan Yıldız",
            "Carlsten",
            "mrb"
        )
    )


    override fun retrieveLessons(): Collection<Lesson> = lessons

    override fun retrieveLesson(name:String): Lesson = lessons.firstOrNull() {it.name == name}
        ?:  throw NoSuchElementException("Could not find a Lesson with name $name")

    override fun createLesson(lesson: Lesson): Lesson {
        if(lessons.any {it.name == lesson.name}) {
            throw IllegalArgumentException("Lesson with name ${lesson.name} already exists")
        }
        lessons.add(lesson)
        return lesson
    }

    override fun deleteLesson(name:String) {
        val currentLesson = lessons.firstOrNull() { it.name == name}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Lesson with name ${name}")

        lessons.remove(currentLesson)
    }

    override fun updateLesson(lesson: Lesson): Lesson {
        val currentLesson = lessons.firstOrNull() { it.name == lesson.name}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Lesson with name ${lesson.name}")

        lessons.remove(currentLesson)
        lessons.add(lesson)
        return lesson
    }

}