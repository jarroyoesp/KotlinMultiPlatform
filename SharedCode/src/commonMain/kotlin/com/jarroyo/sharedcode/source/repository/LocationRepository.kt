package com.jarroyo.sharedcode.repository


import com.jarroyo.kotlinmultiplatform.source.disk.dao.LocationDao
import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.Database
import com.jarroyo.sharedcode.data.LocationModel
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.source.disk.DatabaseCreator
import com.jarroyo.sharedcode.source.disk.DbArgs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocationRepository(
    dbArgs: DbArgs
) {

    private var mDatabase: Database? = DatabaseCreator.getDataBase(dbArgs)

    suspend fun insertLocation(location: Location): Response<List<LocationModel>> {
        val locationDao = LocationDao(mDatabase!!)
        locationDao.insert(location)
        return Response.Success(locationDao.select())
    }

    suspend fun getLocationList(): Response<List<LocationModel>> {
        val locationDao = LocationDao(mDatabase!!)
        return Response.Success(locationDao.select())
    }

    suspend fun deleteLocation(location: Location): Response<List<LocationModel>> {
        val locationDao = LocationDao(mDatabase!!)
        locationDao.delete(location)
        return Response.Success(locationDao.select())
    }

    fun selectFromDb() {
        val locationDao = LocationDao(mDatabase!!)
        locationDao.select()
    }

    fun saveAsync(location: Location, success: (List<LocationModel>) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val locationDao = LocationDao(mDatabase!!)
                locationDao.insert(location)

                val listLocation = getLocationList() as Response.Success
                success(listLocation.data)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }

    fun getLocationListAsync(success: (List<LocationModel>) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            val locationDao = LocationDao(mDatabase!!)
            val locationList = locationDao.select()
            success(locationList)
        }
    }
}