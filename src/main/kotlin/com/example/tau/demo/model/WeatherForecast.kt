package com.example.tau.demo.model

import java.time.LocalDateTime

data class WeatherForecast (
    //val time : LocalDateTime,
    val id : Int,
    val location : String,
    val date: LocalDateTime,
    val degree : Float
)