package com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherByName

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.base.BaseUseCase
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.CurrentWeather
import com.jarroyo.kotlinmultiplatform.repository.WeatherRepository

open class GetWeatherByNameUseCase(val repository: WeatherRepository) : BaseUseCase<GetWeatherByNameRequest, CurrentWeather>() {

    override suspend fun run(): Response<CurrentWeather> {
        return repository.getWeather(request!!.location)
    }
}