package com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.base.BaseUseCase
import com.regin.startmultiplatform.LocationRepository
import domain.Response

open class SaveLocationUseCase(val repository: LocationRepository) : BaseUseCase<SaveLocationRequest, List<LocationModel>>() {

    override suspend fun run(): Response<List<LocationModel>> {
        return repository.insertLocation(request!!.location)
    }
}