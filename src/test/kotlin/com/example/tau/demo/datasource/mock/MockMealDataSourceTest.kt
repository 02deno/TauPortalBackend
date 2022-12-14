package com.example.tau.demo.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.Null
import java.util.*

class MockMealDataSourceTest {
    private val mockMealDataSource = MockMealDataSource()

    @Test
    fun `should provide a collection of meals`() {

        // when
        val meals = mockMealDataSource.retrieveMeals()


        // then
        assertThat(meals.size).isGreaterThanOrEqualTo(2)

    }

    @Test
    fun `should provide some mock data`() {

        // when
        val meals = mockMealDataSource.retrieveMeals()
        // then
        assertThat(meals).allMatch{ it.cal != 0 }
        assertThat(meals).allMatch {it.date != null}

    }
}