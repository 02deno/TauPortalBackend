package com.example.tau.demo.service

import com.example.tau.demo.datasource.BusDataSource
import com.example.tau.demo.datasource.MealDataSource
import com.example.tau.demo.datasource.UserDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BusServiceTest {
    private val dataSource : BusDataSource = mockk(relaxed = true)
    private val busService = BusService(dataSource)
    @Test
    fun `should call its data source to retrieve buses`() {

        // when
        busService.getBuses()

        // then
        verify(exactly = 1) {dataSource.retrieveBuses()}
    }
}