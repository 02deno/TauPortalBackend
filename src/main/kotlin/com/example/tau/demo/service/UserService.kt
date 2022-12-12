package com.example.tau.demo.service

import com.example.tau.demo.datasource.UserDataSource
import com.example.tau.demo.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private val dataSource : UserDataSource) {
    fun getUsers(): Collection<User> = dataSource.retrieveUsers()
    fun getUser(mail: String): User = dataSource.retrieveUser(mail)
    fun addUser(user: User): User = dataSource.createUser(user)
    fun deleteUser(mail: String) = dataSource.deleteUser(mail)
    fun updateUser(user: User): User = dataSource.updateUser(user)

}