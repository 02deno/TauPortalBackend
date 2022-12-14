package com.example.tau.demo.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.Null
import java.util.*

class MockClassroomDataSourceTest {
    private val mockClassroomDataSource = MockClassroomDataSource()

    @Test
    fun `should provide a collection of classrooms`() {

        // when
        val classrooms = mockClassroomDataSource.retrieveClassrooms()


        // then
        assertThat(classrooms.size).isGreaterThanOrEqualTo(1)

    }

    @Test
    fun `should provide some mock data`() {

        // when
        val classrooms = mockClassroomDataSource.retrieveClassrooms()
        // then
        assertThat(classrooms).allMatch {it.location != null}

    }
}