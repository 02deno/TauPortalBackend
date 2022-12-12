package com.example.tau.demo.service

import com.example.tau.demo.datasource.MealDataSource
import com.example.tau.demo.model.Meal
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MealService(private val dataSource : MealDataSource) {
    fun getMeals(): Collection<Meal> = dataSource.retrieveMeals()
    fun getMeal(date:LocalDateTime): Meal = dataSource.retrieveMeal(date)
    fun addMeal(meal: Meal): Meal = dataSource.createMeal(meal)
    fun deleteMeal(date:LocalDateTime) = dataSource.deleteMeal(date)
    fun updateMeal(meal: Meal): Meal = dataSource.updateMeal(meal)
}