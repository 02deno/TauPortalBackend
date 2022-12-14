package com.example.tau.demo.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.Null
import java.time.LocalDateTime
import java.util.*

class MockLessonDataSourceTest {
    private val mockLessonDataSource = MockLessonDataSource()

    @Test
    fun `should provide a collection of lessons`() {

        // when
        val lessons = mockLessonDataSource.retrieveLessons()


        // then
        assertThat(lessons.size).isGreaterThanOrEqualTo(3)

    }

    @Test
    fun `should provide some mock data`() {

        // when
        val lessons = mockLessonDataSource.retrieveLessons()
        // then
        assertThat(lessons).allMatch {it.name.isNotBlank() }
        assertThat(lessons).allMatch {it.classroom != null}
        assertThat(lessons).allMatch {it.date != null}
        assertThat(lessons).allMatch {it.prof != null}
        assertThat(lessons).allMatch {it.assistants != null}
        assertThat(lessons).allMatch {it.link != null}


    }
}