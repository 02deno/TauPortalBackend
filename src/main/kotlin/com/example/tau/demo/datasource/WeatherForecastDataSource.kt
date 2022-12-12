package com.example.tau.demo.datasource

import com.example.tau.demo.model.WeatherForecast

interface WeatherForecastDataSource {
    fun retrieveWeatherForecasts() : Collection<WeatherForecast>
    fun retrieveWeatherForecast(id : Int): WeatherForecast
    fun createWeatherForecast(weatherForecast: WeatherForecast): WeatherForecast
    fun deleteWeatherForecast(id : Int)
    fun updateWeatherForecast(weatherForecast: WeatherForecast): WeatherForecast
}