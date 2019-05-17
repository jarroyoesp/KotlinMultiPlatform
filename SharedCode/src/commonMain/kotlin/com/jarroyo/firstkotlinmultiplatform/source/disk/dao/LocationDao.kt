package com.jarroyo.kotlinmultiplatform.source.disk.dao

//import com.jarroyo.firstkotlinmultiplatform.Database
//import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
//import com.jarroyo.kotlinmultiplatform.domain.model.Location
//
//class LocationDao(database: Database) {
//
//    private val db = database.locationModelQueries
//
//    internal fun insert(item: Location) {
//        db.insertItem(
//            item.cityName,
//            item.country
//        )
//    }
//
//    internal fun delete(location: Location) {
//        db.deleteItem(location.cityName)
//    }
//
//    internal fun select():List<LocationModel> = db.selectAll().executeAsList()
//}