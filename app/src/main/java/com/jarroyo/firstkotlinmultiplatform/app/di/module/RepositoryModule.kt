package com.jarroyo.firstkotlinmultiplatform.app.di.module

import com.jarroyo.firstkotlinmultiplatform.Database
import com.raywenderlich.pokelist.WeatherApi
import com.regin.startmultiplatform.LocationRepository
import com.regin.startmultiplatform.WeatherRepository
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