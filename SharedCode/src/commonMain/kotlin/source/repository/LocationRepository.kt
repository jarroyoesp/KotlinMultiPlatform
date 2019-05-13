package com.regin.startmultiplatform

import com.jarroyo.firstkotlinmultiplatform.Database
import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.source.disk.dao.LocationDao

class LocationRepository(
    private val database: Database
) {

    suspend fun insertLocation(location: Location) {
        val locationDao = LocationDao(database)
        locationDao.insert(location)
    }

    suspend fun getLocationList(): List<LocationModel> {
        val locationDao = LocationDao(database)
        return locationDao.select()
    }

    fun selectFromDb() {
        val locationDao = LocationDao(database)
        locationDao.select()
    }
}