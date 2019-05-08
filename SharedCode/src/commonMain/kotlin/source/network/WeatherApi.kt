package com.raywenderlich.pokelist


import com.jarroyo.kotlinmultiplatform.ApplicationDispatcher
import domain.model.CurrentWeather
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class WeatherApi {

    private val httpClient = HttpClient()

    fun getCurrentWeather(success: (CurrentWeather) -> Unit, failure: (Throwable?) -> Unit){
        GlobalScope.launch(ApplicationDispatcher){
            try {
                val url = "https://api.openweathermap.org/data/2.5/weather?q=Zaragoza&APPID=f11780da3330643cd659bb6dbb4e44a3"
                val json = httpClient.get<String>(url)

               Json.nonstrict.parse(CurrentWeather.serializer(), json)
                   .also(success)
            } catch (ex: Exception){
                failure(ex)
            }
        }
    }

}