package com.jarroyo.firstkotlinmultiplatform.app.di.module

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.deleteLocation.DeleteLocationUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.getLocationList.GetLocationListUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation.SaveLocationUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherByName.GetWeatherByNameUseCase
import com.jarroyo.kotlinmultiplatform.domain.usecase.location.GetLocationMPPListUseCase
import com.jarroyo.kotlinmultiplatform.repository.LocationRepository
import com.jarroyo.kotlinmultiplatform.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule {

    /**
     * LOCATION
     */
    @Provides
    @Singleton
    fun provideGetLocationListUseCase(repository: LocationRepository) = GetLocationListUseCase(repository)


    @Provides
    @Singleton
    fun provideSaveLocationUseCase(repository: LocationRepository) = SaveLocationUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteLocationUseCase(repository: LocationRepository) = DeleteLocationUseCase(repository)


    @Provides
    @Singleton
    fun provideGetLocationMPPListUseCase(repository: LocationRepository) = GetLocationMPPListUseCase(repository)

    /**
     * WEATHER
     */
    @Provides
    @Singleton
    fun provideGetWeatherUseCase(repository: WeatherRepository) = GetWeatherByNameUseCase(repository)
}