package com.example.tau.demo.service

import com.example.tau.demo.datasource.BusDataSource
import com.example.tau.demo.datasource.ClassroomDataSource
import com.example.tau.demo.datasource.MealDataSource
import com.example.tau.demo.datasource.UserDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ClassroomServiceTest {
    private val dataSource : ClassroomDataSource = mockk(relaxed = true)
    private val classroomService = ClassroomService(dataSource)
    @Test
    fun `should call its data source to retrieve classrooms`() {

        // when
        classroomService.getClassrooms()

        // then
        verify(exactly = 1) {dataSource.retrieveClassrooms()}
    }
}