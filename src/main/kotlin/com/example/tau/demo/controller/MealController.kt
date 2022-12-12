package com.example.tau.demo.controller

import com.example.tau.demo.model.Meal
import com.example.tau.demo.service.MealService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/meals")

class MealController(private val service: MealService) {

    // to handle exception if the given ID does not exist
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getMeals() : Collection<Meal> = service.getMeals()

    @GetMapping("/{date}")
    fun getMeal(@PathVariable date : LocalDateTime) = service.getMeal(date)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody meal: Meal) : Meal = service.addMeal(meal)

    @DeleteMapping("/{date}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMeal(@PathVariable date : LocalDateTime) : Unit = service.deleteMeal(date)

    @PatchMapping
    fun updateMeal(@RequestBody meal: Meal): Meal = service.updateMeal(meal)
}