package com.example.tau.demo.service

import com.example.tau.demo.datasource.WeatherForecastDataSource
import com.example.tau.demo.model.WeatherForecast
import org.springframework.stereotype.Service

@Service
class WeatherForecastService(private val dataSource : WeatherForecastDataSource) {
    fun getWeatherForecasts(): Collection<WeatherForecast> = dataSource.retrieveWeatherForecasts()
    fun getWeatherForecast(id:Int): WeatherForecast = dataSource.retrieveWeatherForecast(id)
    fun addWeatherForecast(weatherForecast: WeatherForecast): WeatherForecast = dataSource.createWeatherForecast(weatherForecast)
    fun deleteWeatherForecast(id:Int) = dataSource.deleteWeatherForecast(id)
    fun updateWeatherForecast(weatherForecast: WeatherForecast): WeatherForecast = dataSource.updateWeatherForecast(weatherForecast)
}