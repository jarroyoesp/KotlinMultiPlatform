package com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherList

import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.CurrentWeather
import com.jarroyo.kotlinmultiplatform.domain.usecase.base.BaseUseCase
import com.jarroyo.kotlinmultiplatform.repository.WeatherRepository

open class GetWeatherListUseCase(val repository: WeatherRepository) : BaseUseCase<GetWeatherListRequest, List<Response<CurrentWeather>>>() {

    override suspend fun run(): Response<List<Response<CurrentWeather>>>{
        return repository.getWeatherList(request!!)
    }
}