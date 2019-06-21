package com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.deleteLocation

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.usecase.base.BaseUseCase
import com.jarroyo.kotlinmultiplatform.repository.LocationRepository

open class DeleteLocationUseCase(val repository: LocationRepository) : BaseUseCase<DeleteLocationRequest, List<LocationModel>>() {

    override suspend fun run(): Response<List<LocationModel>> {
        return repository.deleteLocation(request!!.location)
    }
}