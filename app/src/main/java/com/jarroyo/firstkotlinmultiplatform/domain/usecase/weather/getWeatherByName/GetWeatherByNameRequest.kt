package com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherByName

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.base.BaseRequest
import com.jarroyo.kotlinmultiplatform.domain.model.Location

class GetWeatherByNameRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}