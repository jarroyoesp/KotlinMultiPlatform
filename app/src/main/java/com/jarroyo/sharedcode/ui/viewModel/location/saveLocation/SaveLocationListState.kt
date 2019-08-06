package com.jarroyo.sharedcode.ui.viewModel.location.saveLocation

import com.jarroyo.sharedcode.data.LocationModel
import com.jarroyo.sharedcode.domain.Response


sealed class SaveLocationState {
    abstract val response: Response<List<LocationModel>>?
}
data class SuccessSaveLocationState(override val response: Response<List<LocationModel>>) : SaveLocationState()
data class LoadingSaveLocationState(override val response: Response<List<LocationModel>>? = null) : SaveLocationState()
data class ErrorSaveLocationState(override val response: Response<List<LocationModel>>) : SaveLocationState()