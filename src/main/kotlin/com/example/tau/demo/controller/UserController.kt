package com.example.tau.demo.controller

import com.example.tau.demo.model.User
import com.example.tau.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val service: UserService) {

    // to handle exception if the given mail does not exist
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity <String> =
        ResponseEntity(e.message,HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity <String> =
        ResponseEntity(e.message,HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getUsers() : Collection<User> = service.getUsers()

    @GetMapping("/{mail}")
    fun getUser(@PathVariable mail: String) = service.getUser(mail)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user:User) : User = service.addUser(user)

    @DeleteMapping("/{mail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable mail: String) : Unit = service.deleteUser(mail)

    @PatchMapping
    fun updateUser(@RequestBody user:User):User = service.updateUser(user)
}