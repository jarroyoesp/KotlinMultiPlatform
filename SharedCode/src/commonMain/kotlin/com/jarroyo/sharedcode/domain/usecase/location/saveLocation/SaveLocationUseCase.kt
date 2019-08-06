package com.jarroyo.sharedcode.domain.usecase.location.saveLocation

import com.jarroyo.sharedcode.data.LocationModel
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase
import com.jarroyo.sharedcode.repository.LocationRepository

open class SaveLocationUseCase(val repository: LocationRepository) : BaseUseCase<SaveLocationRequest, List<LocationModel>>() {

    override suspend fun run(): Response<List<LocationModel>> {
        return repository.insertLocation(request!!.location)
    }
}