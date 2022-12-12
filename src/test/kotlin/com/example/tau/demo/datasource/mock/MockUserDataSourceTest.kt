package com.example.tau.demo.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class MockUserDataSourceTest {
    private val mockUserDataSource = MockUserDataSource()

    @Test
    fun `should provide a collection of users`() {

        // when
        val users = mockUserDataSource.retrieveUsers()


        // then
        assertThat(users.size).isGreaterThanOrEqualTo(3)

    }

    @Test
    fun `should provide some mock data`() {

        // when
        val users = mockUserDataSource.retrieveUsers()
        // then
        assertThat(users).allMatch {it.name.isNotBlank()}
        assertThat(users).anyMatch {it.surname.isNotBlank()}
        assertThat(users).allMatch {it.mail.isNotBlank()}

    }
}


