package com.jarroyo.sharedcode.domain.usecase.location.getLocationList

import com.jarroyo.sharedcode.data.LocationModel
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase
import com.jarroyo.sharedcode.repository.LocationRepository

open class GetLocationMPPListUseCase(val repository: LocationRepository) : BaseUseCase<Nothing, List<LocationModel>>() {

    override suspend fun run(): Response<List<LocationModel>> {
        return repository.getLocationList()
    }
}