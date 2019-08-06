package com.jarroyo.sharedcode.domain.usecase.weather.getWeather

import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.repository.WeatherRepository

open class GetWeatherByNameUseCase(val repository: WeatherRepository) : BaseUseCase<GetWeatherByNameRequest, CurrentWeather>() {

    override suspend fun run(): Response<CurrentWeather> {
        return repository.getWeather(request!!.location)
    }
}