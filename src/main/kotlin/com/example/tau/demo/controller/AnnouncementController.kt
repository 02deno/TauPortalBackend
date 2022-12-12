package com.example.tau.demo.controller

import com.example.tau.demo.model.Announcement
import com.example.tau.demo.service.AnnouncementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/announcements")

class AnnouncementController(private val service: AnnouncementService) {
    // to handle exception if the given text does not exist
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getAnnouncements() : Collection<Announcement> = service.getAnnouncements()

    @GetMapping("/{text}")
    fun getAnnouncement(@PathVariable text: String) = service.getAnnouncement(text)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody announcement: Announcement) : Announcement = service.addAnnouncement(announcement)

    @DeleteMapping("/{text}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAnnouncement(@PathVariable text: String) : Unit = service.deleteAnnouncement(text)

    @PatchMapping
    fun updateAnnouncement(@RequestBody announcement: Announcement): Announcement = service.updateAnnouncement(announcement)
}