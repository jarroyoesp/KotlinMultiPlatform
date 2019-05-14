package com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherByName.GetWeatherByNameRequest
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherByName.GetWeatherByNameUseCase
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.ErrorGetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.GetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.SuccessGetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.utils.launchSilent
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.CurrentWeather
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherViewModel
@Inject
constructor(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private var job: Job = Job()

    var getWeatherByLocationLiveData = MutableLiveData<GetWeatherByLocationState>()

    init {
    }

    /**
     * GET WEATHER LOCATION
     */
    fun getWeatherByLocation(location: Location) = launchSilent(coroutineContext, job) {
        val request = GetWeatherByNameRequest(location)
        val response = getWeatherByNameUseCase.execute(request)
        processGetWeatherLocationListResponse(response)
    }

    fun processGetWeatherLocationListResponse(response: Response<CurrentWeather>) {
        if (response is Response.Success) {
            getWeatherByLocationLiveData.postValue(
                SuccessGetWeatherByLocationState(
                    response
                )
            )
        } else if (response is Response.Error) {
            getWeatherByLocationLiveData.postValue(
                ErrorGetWeatherByLocationState(
                    response
                )
            )
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}