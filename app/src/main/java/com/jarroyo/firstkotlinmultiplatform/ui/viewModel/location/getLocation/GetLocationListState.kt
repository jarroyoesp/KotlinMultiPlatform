package com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import domain.Response


sealed class GetLocationListState {
    abstract val response: Response<List<LocationModel>>?
}
data class SuccessGetLocationListState(override val response: Response<List<LocationModel>>) : GetLocationListState()
data class LoadingGetLocationListState(override val response: Response<List<LocationModel>>? = null) : GetLocationListState()
data class ErrorGetLocationListState(override val response: Response<List<LocationModel>>) : GetLocationListState()