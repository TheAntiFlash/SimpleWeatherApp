package com.example.simpleweatherapp.di

import com.example.simpleweatherapp.data.location.LocationTrackerImpl
import com.example.simpleweatherapp.data.repository.WeatherRepositoryImpl
import com.example.simpleweatherapp.domain.location.LocationTracker
import com.example.simpleweatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}