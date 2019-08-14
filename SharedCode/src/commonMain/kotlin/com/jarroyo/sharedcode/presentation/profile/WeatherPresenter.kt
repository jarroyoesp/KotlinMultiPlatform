package com.jarroyo.sharedcode.presentation

import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameRequest
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WeatherPresenter(
    private var getCurrentWeatherByNameUseCase: GetWeatherByNameUseCase,
    coroutineContext: CoroutineContext = ApplicationDispatcher
) : BasePresenter<WeatherView>(coroutineContext) {

    override fun onViewAttached(view: WeatherView) {

    }


    fun getCurrentWeather(location: Location) {
        view?.showHideLoading(true)
        scope.launch {
            val request = GetWeatherByNameRequest(location)
            val response = getCurrentWeatherByNameUseCase.execute(request)
            if (response is Response.Success) {
                view?.onSuccessGetCurrentWeather(response.data)
            } else if (response is Response.Error){
                view?.onErrorGetCurrentWeather(response.exception)
            }
            view?.showHideLoading(false)
        }
    }
}

interface WeatherView {

    fun onSuccessGetCurrentWeather(currentWeather: CurrentWeather)
    fun onErrorGetCurrentWeather(throwable: Throwable)

    fun showHideLoading(visible: Boolean)
}