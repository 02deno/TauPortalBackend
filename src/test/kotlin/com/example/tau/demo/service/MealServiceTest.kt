package com.example.tau.demo.service

import com.example.tau.demo.datasource.MealDataSource
import com.example.tau.demo.datasource.UserDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MealServiceTest {
    private val dataSource : MealDataSource = mockk(relaxed = true)
    private val mealService = MealService(dataSource)
    @Test
    fun `should call its data source to retrieve meals`() {

        // when
        mealService.getMeals()

        // then
        verify(exactly = 1) {dataSource.retrieveMeals()}
    }
}