package com.jarroyo.firstkotlinmultiplatform.app.di.module

import com.jarroyo.firstkotlinmultiplatform.Database
import com.jarroyo.kotlinmultiplatform.repository.LocationRepository
import com.jarroyo.kotlinmultiplatform.repository.WeatherRepository
import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLocationRepository(
        database: Database
    ) = LocationRepository(database)

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApi: WeatherApi
    ) = WeatherRepository(weatherApi)

}