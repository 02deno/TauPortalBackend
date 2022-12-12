package com.example.tau.demo.datasource.mock

import com.example.tau.demo.datasource.WeatherForecastDataSource
import com.example.tau.demo.model.WeatherForecast
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

@Repository
class MockWeatherForecastDataSource : WeatherForecastDataSource {
    val weatherForecasts = mutableListOf<WeatherForecast>(
        WeatherForecast(1,"Beykoz", LocalDateTime.of(2022, 6, 30, 12,0),3.5f),
        WeatherForecast(2, "Beykoz", LocalDateTime.of(2022, 6, 30, 14, 0,0),4f) ,
        WeatherForecast(3, "Beykoz", LocalDateTime.of(2022, 6, 30, 16, 0,0),5.5f)
        
    )

    override fun retrieveWeatherForecasts(): Collection<WeatherForecast> = weatherForecasts

    override fun retrieveWeatherForecast(id:Int): WeatherForecast = weatherForecasts.firstOrNull() {it.id == id}
        ?:  throw NoSuchElementException("Could not find a WeatherForecast with id $id")

    override fun createWeatherForecast(weatherForecast: WeatherForecast): WeatherForecast {
        if(weatherForecasts.any {it.id == weatherForecast.id}) {
            throw IllegalArgumentException("WeatherForecast with id ${weatherForecast.id} already exists")
        }
        weatherForecasts.add(weatherForecast)
        return weatherForecast
    }

    override fun deleteWeatherForecast(id : Int) {
        val currentWeatherForecast = weatherForecasts.firstOrNull() { it.id == id}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a WeatherForecast with id ${id}")

        weatherForecasts.remove(currentWeatherForecast)
    }

    override fun updateWeatherForecast(weatherForecast: WeatherForecast): WeatherForecast {
        val currentWeatherForecast = weatherForecasts.firstOrNull() { it.id== weatherForecast.id}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a WeatherForecast with did ${weatherForecast.id}")

        weatherForecasts.remove(currentWeatherForecast)
        weatherForecasts.add(weatherForecast)
        return weatherForecast
    }

}