package com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.deleteLocation

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.base.BaseUseCase
import com.regin.startmultiplatform.LocationRepository
import domain.Response

open class DeleteLocationUseCase(val repository: LocationRepository) : BaseUseCase<DeleteLocationRequest, List<LocationModel>>() {

    override suspend fun run(): Response<List<LocationModel>> {
        return repository.deleteLocation(request!!.location)
    }
}