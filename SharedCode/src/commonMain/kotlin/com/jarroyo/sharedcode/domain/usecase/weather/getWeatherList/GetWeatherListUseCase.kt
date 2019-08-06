package com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList

import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase
import com.jarroyo.sharedcode.repository.WeatherRepository

open class GetWeatherListUseCase(val repository: WeatherRepository) : BaseUseCase<GetWeatherListRequest, List<Response<CurrentWeather>>>() {

    override suspend fun run(): Response<List<Response<CurrentWeather>>>{
        return repository.getWeatherList(request!!)
    }
}