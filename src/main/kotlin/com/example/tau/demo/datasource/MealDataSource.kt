package com.example.tau.demo.datasource

import com.example.tau.demo.model.Meal
import java.time.LocalDateTime

interface MealDataSource {
    fun retrieveMeals() : Collection<Meal>
    fun retrieveMeal(date:LocalDateTime): Meal
    fun createMeal(meal: Meal): Meal
    fun deleteMeal(date:LocalDateTime)
    fun updateMeal(meal: Meal): Meal
}