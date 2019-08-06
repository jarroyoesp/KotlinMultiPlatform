package com.jarroyo.sharedcode.domain.usecase.location.deleteLocation

import com.jarroyo.sharedcode.data.LocationModel
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase
import com.jarroyo.sharedcode.repository.LocationRepository

open class DeleteLocationUseCase(val repository: LocationRepository) : BaseUseCase<DeleteLocationRequest, List<LocationModel>>() {

    override suspend fun run(): Response<List<LocationModel>> {
        return repository.deleteLocation(request!!.location)
    }
}