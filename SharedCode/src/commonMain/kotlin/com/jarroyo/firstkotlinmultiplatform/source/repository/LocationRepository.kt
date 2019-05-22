package com.jarroyo.kotlinmultiplatform.repository

import com.jarroyo.firstkotlinmultiplatform.ApplicationDispatcher
import com.jarroyo.firstkotlinmultiplatform.Database
import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.getDataBase
import com.jarroyo.kotlinmultiplatform.source.disk.dao.LocationDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LocationRepository(
    //private val database: Database
) {

    val database: Database = getDataBase()

    suspend fun insertLocation(location: Location): Response<List<LocationModel>> {
        val locationDao = LocationDao(database)
        locationDao.insert(location)
        return Response.Success(locationDao.select())
    }

    suspend fun getLocationList(): Response<List<LocationModel>> {
        val locationDao = LocationDao(database)
        return Response.Success(locationDao.select())
    }

    suspend fun deleteLocation(location: Location): Response<List<LocationModel>> {
        val locationDao = LocationDao(database)
        locationDao.delete(location)
        return Response.Success(locationDao.select())
    }

    fun selectFromDb() {
        val locationDao = LocationDao(database)
        locationDao.select()
    }

    fun saveAsync(success: (Location) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val locationDao = LocationDao(database)
                val location = Location("Zaragoza", "Spain")
                locationDao.insert(location)
                success(location)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }

    fun getLocationListAsync(success: (List<LocationModel>) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            val locationDao = LocationDao(database)
            val locationList = locationDao.select()
            success(locationList)
        }
    }
}