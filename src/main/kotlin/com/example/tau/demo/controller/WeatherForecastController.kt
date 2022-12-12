package com.example.tau.demo.controller

import com.example.tau.demo.model.WeatherForecast
import com.example.tau.demo.service.WeatherForecastService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/weatherforecasts")

class WeatherForecastController(private val service: WeatherForecastService) {

    // to handle exception if the given ID does not exist
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getWeatherForecasts() : Collection<WeatherForecast> = service.getWeatherForecasts()

    @GetMapping("/{id}")
    fun getWeatherForecast(@PathVariable id : Int) = service.getWeatherForecast(id)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody weatherForecast: WeatherForecast) : WeatherForecast = service.addWeatherForecast(weatherForecast)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteWeatherForecast(@PathVariable id : Int) : Unit = service.deleteWeatherForecast(id)

    @PatchMapping
    fun updateWeatherForecast(@RequestBody weatherForecast: WeatherForecast): WeatherForecast = service.updateWeatherForecast(weatherForecast)
}