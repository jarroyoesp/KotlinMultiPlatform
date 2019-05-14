package com.jarroyo.kotlinmultiplatform

import com.jarroyo.kotlinmultiplatform.domain.model.CurrentWeather
import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi


expect fun platformName(): String

fun requestData(success: (CurrentWeather) -> Unit, failure: (Throwable?) -> Unit) {

  WeatherApi().getCurrentWeather(success = {
    success(it)

  }, failure = {
    failure(it)
  })
}




