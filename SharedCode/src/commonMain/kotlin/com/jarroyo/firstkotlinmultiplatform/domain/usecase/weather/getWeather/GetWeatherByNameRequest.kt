package com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeather

import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.domain.usecase.base.BaseRequest

class GetWeatherByNameRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}