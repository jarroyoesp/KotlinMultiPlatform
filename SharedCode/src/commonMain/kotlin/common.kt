package com.jarroyo.kotlinmultiplatform

import com.raywenderlich.pokelist.WeatherApi
import domain.model.CurrentWeather


expect fun platformName(): String

fun requestData(success: (CurrentWeather) -> Unit, failure: (Throwable?) -> Unit) {

  WeatherApi().getCurrentWeather(success = {
    success(it)

  }, failure = {
    failure(it)
  })
}


