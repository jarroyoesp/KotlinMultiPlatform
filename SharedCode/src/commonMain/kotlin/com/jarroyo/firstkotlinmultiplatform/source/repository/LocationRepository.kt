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


class LocationRepository() {
    private var database: Database? = null

    /**
     * Constructor used by Android. On Android to create the SQLDelight database we need the context, so we need to create
     * on the Android app.
     *
     * Database on iOS is created on Init. We call getDataBase()
     */
    constructor(mDatabase: Database) : this() {
        database = mDatabase
    }

    /**
     * On iOS, due to when Location Repository is created there is no instance of database, when it is init database is
     * got from iOSMain
     */
    init {
        if (database == null) {
            database = getDataBase()
        }
    }


    suspend fun insertLocation(location: Location): Response<List<LocationModel>> {
        val locationDao = LocationDao(database!!)
        locationDao.insert(location)
        return Response.Success(locationDao.select())
    }

    suspend fun getLocationList(): Response<List<LocationModel>> {
        val locationDao = LocationDao(database!!)
        return Response.Success(locationDao.select())
    }

    suspend fun deleteLocation(location: Location): Response<List<LocationModel>> {
        val locationDao = LocationDao(database!!)
        locationDao.delete(location)
        return Response.Success(locationDao.select())
    }

    fun selectFromDb() {
        val locationDao = LocationDao(database!!)
        locationDao.select()
    }

    fun saveAsync(location: Location, success: (List<LocationModel>) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val locationDao = LocationDao(database!!)
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
            val locationDao = LocationDao(database!!)
            val locationList = locationDao.select()
            success(locationList)
        }
    }
}