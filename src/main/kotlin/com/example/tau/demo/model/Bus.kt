package com.example.tau.demo.model

import java.time.LocalDateTime

data class Bus(
    val ID: Int,
    val departure_time: LocalDateTime,
    val departure_point:String
)