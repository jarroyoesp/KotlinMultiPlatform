package com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherList

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.kotlinmultiplatform.domain.usecase.base.BaseRequest

class GetWeatherListRequest(var locationList: List<LocationModel>) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}