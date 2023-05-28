package com.example.simpleweatherapp.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.simpleweatherapp.data.remote.WeatherDataDto
import com.example.simpleweatherapp.data.remote.WeatherDto
import com.example.simpleweatherapp.domain.weather.WeatherData
import com.example.simpleweatherapp.domain.weather.WeatherInfo
import com.example.simpleweatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap() : Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
       IndexedWeatherData(
           index = index,
           data =  WeatherData(
               time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
               temperatureCelsius = temperature,
               humidity = humidity,
               weatherType = WeatherType.fromWMO(weatherCode),
               pressure = pressure,
               windSpeed = windSpeed
           )
       )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo() : WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = when {
            now.minute < 30 -> now.hour
            now.hour == 23 -> 12
            else -> now.hour + 1
        }
        it.time.hour == hour
    }
    return WeatherInfo(
        currentWeatherData = currentWeatherData,
        weatherDataPerDay = weatherDataMap
    )

}