package com.jarroyo.sharedcode.domain.usecase.weather.getWeather

import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.domain.usecase.base.BaseRequest

class GetWeatherByNameRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}