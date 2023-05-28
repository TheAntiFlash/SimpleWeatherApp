package com.example.simpleweatherapp.di

import com.example.simpleweatherapp.data.location.LocationTrackerImpl
import com.example.simpleweatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTrackerImpl: LocationTrackerImpl): LocationTracker
}