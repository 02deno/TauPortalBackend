package com.example.tau.demo.datasource.mock

import com.example.tau.demo.datasource.MealDataSource
import com.example.tau.demo.model.Meal
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

@Repository
class MockMealDataSource : MealDataSource {
    val meals = mutableListOf<Meal>(
        Meal(UUID.randomUUID(),LocalDateTime.of(2022, 12, 12,11,30),500),
        Meal(UUID.randomUUID(),LocalDateTime.of(2022, 12, 13,11,30),600),
        Meal(UUID.randomUUID(),LocalDateTime.of(2022, 12, 14,11,30),550)
        
    )

    override fun retrieveMeals(): Collection<Meal> = meals

    override fun retrieveMeal(date: LocalDateTime): Meal = meals.firstOrNull() {it.date == date}
        ?:  throw NoSuchElementException("Could not find a Meal with date $date")

    override fun createMeal(Meal: Meal): Meal {
        if(meals.any {it.date == Meal.date}) {
            throw IllegalArgumentException("Meal with date ${Meal.date} already exists")
        }
        meals.add(Meal)
        return Meal
    }

    override fun deleteMeal(date: LocalDateTime) {
        val currentMeal = meals.firstOrNull() { it.date == date}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Meal with date ${date}")

        meals.remove(currentMeal)
    }

    override fun updateMeal(meal: Meal): Meal {
        val currentMeal = meals.firstOrNull() { it.date == meal.date}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Meal with date ${meal.date}")

        meals.remove(currentMeal)
        meals.add(meal)
        return meal
    }

}