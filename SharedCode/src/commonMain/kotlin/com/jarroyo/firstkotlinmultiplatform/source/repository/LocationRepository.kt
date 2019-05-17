package com.jarroyo.kotlinmultiplatform.repository

//class LocationRepository(
//    private val database: Database
//) {
//
//    suspend fun insertLocation(location: Location): Response<List<LocationModel>> {
//        val locationDao = LocationDao(database)
//        locationDao.insert(location)
//        return Response.Success(locationDao.select())
//    }
//
//    suspend fun getLocationList(): Response<List<LocationModel>> {
//        val locationDao = LocationDao(database)
//        return Response.Success(locationDao.select())
//    }
//
//    suspend fun deleteLocation(location: Location): Response<List<LocationModel>> {
//        val locationDao = LocationDao(database)
//        locationDao.delete(location)
//        return Response.Success(locationDao.select())
//    }
//
//    fun selectFromDb() {
//        val locationDao = LocationDao(database)
//        locationDao.select()
//    }
//}