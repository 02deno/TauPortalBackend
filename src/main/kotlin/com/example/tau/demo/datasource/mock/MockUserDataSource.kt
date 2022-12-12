package com.example.tau.demo.datasource.mock

import com.example.tau.demo.datasource.UserDataSource
import com.example.tau.demo.model.User
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.util.UUID

@Repository
class MockUserDataSource : UserDataSource {

    val users = mutableListOf<User>(User(
        UUID.randomUUID(),
        "Deniz",
        "Ozdemirli",
        "deno@gmail.com",
        "123",
        "05385427348",
        "student"),

        User(
            UUID.randomUUID(),
            "Dilanur",
            "Gider",
            "dila@gmail.com",
            "123",
            "05385427348",
            "student"),

        User(
            UUID.randomUUID(),
            "Hatice",
            "Turan",
            "hatice@gmail.com",
            "123",
            "05385427348",
            "student"),

        User(
            UUID.randomUUID(),
            "Hasan Can",
            "Dizdar",
            "hasan@gmail.com",
            "123",
            "05385427348",
            "student")
    )

    override fun retrieveUsers(): Collection<User> = users

    override fun retrieveUser(mail: String): User = users.firstOrNull() {it.mail == mail}
        ?:  throw NoSuchElementException("Could not find a user with mail $mail")

    override fun createUser(user: User): User {
        if(users.any {it.mail == user.mail}) {
            throw IllegalArgumentException("User with mail ${user.mail} already exists")
        }
        users.add(user)
        return user
    }

    override fun deleteUser(mail: String) {
        val currentUser = users.firstOrNull() { it.mail == mail}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a user with mail ${mail}")

        users.remove(currentUser)
    }

    override fun updateUser(user: User): User {
        val currentUser = users.firstOrNull() { it.mail == user.mail}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a user with mail ${user.mail}")

        users.remove(currentUser)
        users.add(user)
        return user
    }

}