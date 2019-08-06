package com.jarroyo.sharedcode.ui.viewModel.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jarroyo.sharedcode.data.LocationModel
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameRequest
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList.GetWeatherListRequest
import com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList.GetWeatherListUseCase
import com.jarroyo.sharedcode.ui.viewModel.weather.getWeatherByLocation.ErrorGetWeatherByLocationState
import com.jarroyo.sharedcode.ui.viewModel.weather.getWeatherByLocation.GetWeatherByLocationState
import com.jarroyo.sharedcode.ui.viewModel.weather.getWeatherByLocation.SuccessGetWeatherByLocationState
import com.jarroyo.sharedcode.utils.launchSilent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherViewModel
@Inject
constructor(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherListUseCase: GetWeatherListUseCase,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private var job: Job = Job()

    var getWeatherByLocationLiveData = MutableLiveData<GetWeatherByLocationState>()
    var getWeatherListStateLiveData = MutableLiveData<List<GetWeatherByLocationState>>()

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

    /**
     * GET WEATHER LIST
     */
    fun getWeatherList(locationList: List<LocationModel>) = launchSilent(coroutineContext, job) {
        val request = GetWeatherListRequest(locationList)
        val response = getWeatherListUseCase.execute(request)
        processWeatherListResponse(response)
    }

    private fun processWeatherListResponse(response: Response<List<Response<CurrentWeather>>>) {
        if (response is Response.Success) {
            var weatherList = mutableListOf<GetWeatherByLocationState>()

            for (responseWeather in response.data) {
                if (responseWeather is Response.Success) {

                    weatherList.add(
                        SuccessGetWeatherByLocationState(
                            responseWeather
                        )
                    )
                }
            }
            getWeatherListStateLiveData.postValue(weatherList)
        }

        else if (response is Response.Error) {
            //weatherListStateLiveData.postValue(ErrorCurrentWeatherState(response))
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}