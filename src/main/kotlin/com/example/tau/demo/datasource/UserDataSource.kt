package com.example.tau.demo.datasource

import com.example.tau.demo.model.User

interface UserDataSource {

    fun retrieveUsers() : Collection<User>
    fun retrieveUser(mail: String): User
    fun createUser(user: User): User
    fun deleteUser(mail: String)
    fun updateUser(user: User): User
}