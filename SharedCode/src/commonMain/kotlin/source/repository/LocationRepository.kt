package com.regin.startmultiplatform

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.source.disk.dao.LocationDao

class LocationRepository(
    private val locationDao: LocationDao
) {

    suspend fun insertLocation(location: Location) {
        locationDao.insert(location)
    }

    suspend fun getLocationList(): List<LocationModel> {
        return locationDao.select()
    }

    fun selectFromDb() = locationDao.select()
}