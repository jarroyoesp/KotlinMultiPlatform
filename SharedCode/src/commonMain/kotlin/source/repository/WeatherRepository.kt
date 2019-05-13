package com.regin.startmultiplatform

import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.raywenderlich.pokelist.WeatherApi
import domain.Response
import domain.model.CurrentWeather

class WeatherRepository(
    private val weatherApi: WeatherApi
) {

    suspend fun getWeather(location: Location): Response<CurrentWeather> {
        return weatherApi.getWeather(location)
    }
}