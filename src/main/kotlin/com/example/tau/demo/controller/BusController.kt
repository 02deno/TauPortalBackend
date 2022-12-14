package com.example.tau.demo.controller

import com.example.tau.demo.model.Bus
import com.example.tau.demo.service.BusService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/buses")

class BusController(private val service: BusService) {

    // to handle exception if the given ID does not exist
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getBuss() : Collection<Bus> = service.getBuses()

    @GetMapping("/{id}")
    fun getBus(@PathVariable id: Int) = service.getBus(id)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBus(@RequestBody bus: Bus) : Bus = service.addBus(bus)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBus(@PathVariable id: Int) : Unit = service.deleteBus(id)

    @PatchMapping
    fun updateBus(@RequestBody bus: Bus): Bus = service.updateBus(bus)
}