package com.example.tau.demo.service

import com.example.tau.demo.datasource.BusDataSource
import com.example.tau.demo.datasource.LessonDataSource
import com.example.tau.demo.datasource.MealDataSource
import com.example.tau.demo.datasource.UserDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LessonServiceTest {
    private val dataSource : LessonDataSource = mockk(relaxed = true)
    private val lessonService = LessonService(dataSource)
    @Test
    fun `should call its data source to retrieve lessons`() {

        // when
        lessonService.getLessons()

        // then
        verify(exactly = 1) {dataSource.retrieveLessons()}
    }
}