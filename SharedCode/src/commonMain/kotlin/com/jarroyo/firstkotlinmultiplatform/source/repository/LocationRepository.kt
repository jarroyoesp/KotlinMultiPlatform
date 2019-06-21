package com.jarroyo.kotlinmultiplatform.repository

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.firstkotlinmultiplatform.source.disk.DatabaseCreator
import com.jarroyo.firstkotlinmultiplatform.source.disk.DbArgs
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.source.disk.dao.LocationDao

class LocationRepository(
    //private val database: Database,
    private val dbArgs: DbArgs
) {

    suspend fun insertLocation(location: Location): Response<List<LocationModel>> {
        val database = DatabaseCreator.getDataBase(dbArgs)

        val locationDao = LocationDao(database)
        locationDao.insert(location)
        return Response.Success(locationDao.select())
    }

    suspend fun getLocationList(): Response<List<LocationModel>> {
        val database = DatabaseCreator.getDataBase(dbArgs)

        val locationDao = LocationDao(database)
        return Response.Success(locationDao.select())
    }

    suspend fun deleteLocation(location: Location): Response<List<LocationModel>> {
        val database = DatabaseCreator.getDataBase(dbArgs)

        val locationDao = LocationDao(database)
        locationDao.delete(location)
        return Response.Success(locationDao.select())
    }

    fun selectFromDb() {
        val database = DatabaseCreator.getDataBase(dbArgs)

        val locationDao = LocationDao(database)
        locationDao.select()
    }
}