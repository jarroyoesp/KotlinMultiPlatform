package com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.getLocationList

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.base.BaseUseCase
import com.regin.startmultiplatform.LocationRepository
import domain.Response

open class GetLocationListUseCase(val repository: LocationRepository) : BaseUseCase<Nothing, List<LocationModel>>() {

    override suspend fun run(): Response<List<LocationModel>> {
        return repository.getLocationList()
    }
}