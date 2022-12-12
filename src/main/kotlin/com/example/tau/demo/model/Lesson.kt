package com.example.tau.demo.model

import java.time.LocalDateTime
import java.util.*

data class Lesson(
    val lesson_id : UUID,
    val name : String,
    var classroom : Classroom,
    var date : LocalDateTime,
    var prof : String,
    var assistants : String,
    val link : String
)