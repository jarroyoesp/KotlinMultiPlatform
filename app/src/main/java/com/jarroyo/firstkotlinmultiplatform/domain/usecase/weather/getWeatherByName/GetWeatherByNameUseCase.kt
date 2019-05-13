package com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherByName

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.base.BaseUseCase
import com.regin.startmultiplatform.WeatherRepository
import domain.Response
import domain.model.CurrentWeather

open class GetWeatherByNameUseCase(val repository: WeatherRepository) : BaseUseCase<GetWeatherByNameRequest, CurrentWeather>() {

    override suspend fun run(): Response<CurrentWeather> {
        return repository.getWeather(request!!.location)
    }
}