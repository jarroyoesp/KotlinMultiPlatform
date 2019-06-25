package com.jarroyo.firstkotlinmultiplatform.di

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.deleteLocation.DeleteLocationUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation.SaveLocationUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherList.GetWeatherListUseCase
import com.jarroyo.firstkotlinmultiplatform.presentation.ProfilePresenter
import com.jarroyo.firstkotlinmultiplatform.source.disk.DbArgs
import com.jarroyo.kotlinmultiplatform.domain.usecase.location.getLocationList.GetLocationMPPListUseCase
import com.jarroyo.kotlinmultiplatform.repository.LocationRepository
import com.jarroyo.kotlinmultiplatform.repository.WeatherRepository
import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InjectorCommon {

    /**
     * WEATHER
     */
    private val weatherApi: WeatherApi by lazy { WeatherApi() }


    private val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(weatherApi)
    }

    fun provideGetWeatherListUseCase(): GetWeatherListUseCase {
        return GetWeatherListUseCase(weatherRepository)
    }

    fun provideGetWeatherUseCase(): GetWeatherByNameUseCase {
        return GetWeatherByNameUseCase(weatherRepository)
    }

    /**
     * LOCATION
     */
    lateinit var mDbArgs: DbArgs

    private val locationRepository: LocationRepository by lazy {
        LocationRepository(mDbArgs)
    }

    fun provideGetLocationMPPUseCase(dbArgs: DbArgs): GetLocationMPPListUseCase {
        mDbArgs = dbArgs
        return GetLocationMPPListUseCase(locationRepository)
    }

    fun provideDeleteLocationUseCase(dbArgs: DbArgs): DeleteLocationUseCase {
        mDbArgs = dbArgs
        return DeleteLocationUseCase(locationRepository)
    }

    fun provideSaveLocationUseCase(dbArgs: DbArgs): SaveLocationUseCase {
        mDbArgs = dbArgs
        return SaveLocationUseCase(locationRepository)
    }

    /**
     * PRESENTER
     */
    fun provideProfilePresenter(dbArgs: DbArgs): ProfilePresenter {
        return ProfilePresenter(provideGetLocationMPPUseCase(dbArgs), provideGetWeatherUseCase(), provideSaveLocationUseCase(dbArgs))
    }

}
