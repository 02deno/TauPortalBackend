package com.example.tau.demo.service

import com.example.tau.demo.datasource.UserDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserServiceTest {

    private val dataSource : UserDataSource = mockk(relaxed = true)
    private val userService = UserService(dataSource)
    @Test
    fun `should call its data source to retrieve users`() {

        // when
        userService.getUsers()

        // then
        verify(exactly = 1) {dataSource.retrieveUsers()}
    }
}