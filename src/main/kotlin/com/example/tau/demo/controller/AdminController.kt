package com.example.tau.demo.controller

import com.example.tau.demo.model.Admin
import com.example.tau.demo.service.AdminService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/admins")

class AdminController(private val service: AdminService) {

    // to handle exception if the given ID does not exist
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getAdmins() : Collection<Admin> = service.getAdmins()

    @GetMapping("/{no}")
    fun getAdmin(@PathVariable no : String) = service.getAdmin(no)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addAdmin(@RequestBody admin: Admin) : Admin = service.addAdmin(admin)

    @DeleteMapping("/{no}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAdmin(@PathVariable  no : String) : Unit = service.deleteAdmin(no)

    @PatchMapping
    fun updateAdmin(@RequestBody admin: Admin): Admin = service.updateAdmin(admin)
}