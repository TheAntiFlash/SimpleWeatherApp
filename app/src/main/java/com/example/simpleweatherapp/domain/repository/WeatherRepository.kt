package com.example.simpleweatherapp.domain.repository

import com.example.simpleweatherapp.domain.util.Resource
import com.example.simpleweatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}