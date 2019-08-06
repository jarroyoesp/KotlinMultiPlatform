package com.jarroyo.kotlinmultiplatform.source.network


import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class WeatherApi {

    private val httpClient = HttpClient()

    suspend fun getCurrentWeather(location: Location, success: (CurrentWeather) -> Unit, failure: (Throwable?) -> Unit) {
        try {
            val url =
                "https://api.openweathermap.org/data/2.5/weather?q=${location.cityName}&APPID=f11780da3330643cd659bb6dbb4e44a3&units=metric"
            val json = httpClient.get<String>(url)

            Json.nonstrict.parse(CurrentWeather.serializer(), json)
                .also(success)
        } catch (ex: Exception) {
            failure(ex)
        }
    }

    suspend fun getWeather(location: Location): Response<CurrentWeather> {
        try {
            val url =
                "https://api.openweathermap.org/data/2.5/weather?q=${location.cityName}&APPID=f11780da3330643cd659bb6dbb4e44a3&units=metric"
            val json = httpClient.get<String>(url)
            val currentWeather = Json.nonstrict.parse(CurrentWeather.serializer(), json)

            return Response.Success(currentWeather)
        } catch (e: Exception) {
            return Response.Error(e)
        }
    }

}