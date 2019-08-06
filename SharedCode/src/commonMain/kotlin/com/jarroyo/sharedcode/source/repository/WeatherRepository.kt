package com.jarroyo.sharedcode.repository

import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList.GetWeatherListRequest
import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherRepository(
    private val weatherApi: WeatherApi
) {

    suspend fun getWeather(location: Location): Response<CurrentWeather> {
        return weatherApi.getWeather(location)
    }

    suspend fun getWeatherList(request: GetWeatherListRequest): Response<List<Response<CurrentWeather>>> {

        var list = mutableListOf<Response<CurrentWeather>>()
        for (locationModel in request.locationList) {
            val weather = getWeather(Location(locationModel.city_name))

            list.add(weather)
        }
        return Response.Success(list)
    }

    fun getCurrentWeather(location: Location, success: (CurrentWeather) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            weatherApi.getCurrentWeather(location, success, failure)
        }
    }
}