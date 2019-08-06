package com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList

import com.jarroyo.sharedcode.data.LocationModel
import com.jarroyo.sharedcode.domain.usecase.base.BaseRequest

class GetWeatherListRequest(var locationList: List<LocationModel>) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}