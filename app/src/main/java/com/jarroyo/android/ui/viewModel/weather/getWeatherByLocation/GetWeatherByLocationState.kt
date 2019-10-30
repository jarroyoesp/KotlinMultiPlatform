package com.jarroyo.android.ui.viewModel.weather.getWeatherByLocation

import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.CurrentWeather

sealed class GetWeatherByLocationState {
    abstract val response: Response<CurrentWeather>?
}
data class SuccessGetWeatherByLocationState(override val response: Response<CurrentWeather>) : GetWeatherByLocationState()
data class LoadingGetWeatherByLocationState(override val response: Response<CurrentWeather>? = null) : GetWeatherByLocationState()
data class ErrorGetWeatherByLocationState(override val response: Response<CurrentWeather>) : GetWeatherByLocationState()