package com.jarroyo.firstkotlinmultiplatform.presentation

import com.jarroyo.firstkotlinmultiplatform.ApplicationDispatcher
import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation.SaveLocationRequest
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation.SaveLocationUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeather.GetWeatherByNameRequest
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.CurrentWeather
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.domain.usecase.location.getLocationList.GetLocationMPPListUseCase
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfilePresenter(
    private var getLocationMPPListUseCase: GetLocationMPPListUseCase,
    private var getCurrentWeatherByNameUseCase: GetWeatherByNameUseCase,
    private var saveLocationUseCase: SaveLocationUseCase,
    coroutineContext: CoroutineContext = ApplicationDispatcher
) : BasePresenter<ProfileView>(coroutineContext) {

    override fun onViewAttached(view: ProfileView) {

    }

    fun getLocationList() {
        view?.showHideLoading(true)
        scope.launch {

            val response = getLocationMPPListUseCase.execute()
            if (response is Response.Success) {
                view?.onSuccessGetLocationList(response.data)
            } else if (response is Response.Error){
                view?.onErrorGetLocationList(response.exception)
            }
            view?.showHideLoading(false)
        }
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

    fun saveLocation(location: Location) {
        view?.showHideLoading(true)
        scope.launch {
            val request = SaveLocationRequest(location)
            val response = saveLocationUseCase.execute(request)
            if (response is Response.Success) {
                view?.onSuccessSaveLocation(response.data)
            } else if (response is Response.Error){
                view?.onErrorSaveLocation(response.exception)
            }
            view?.showHideLoading(false)
        }
    }
}

interface ProfileView {
    fun onSuccessGetLocationList(locationList: List<LocationModel>)
    fun onErrorGetLocationList(throwable: Throwable)

    fun onSuccessGetCurrentWeather(currentWeather: CurrentWeather)
    fun onErrorGetCurrentWeather(throwable: Throwable)

    fun onSuccessSaveLocation(locationList: List<LocationModel>)
    fun onErrorSaveLocation(throwable: Throwable)

    fun showHideLoading(visible: Boolean)
}