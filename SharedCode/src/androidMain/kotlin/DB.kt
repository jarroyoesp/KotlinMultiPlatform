package com.jarroyo.kotlinmultiplatform

import com.jarroyo.sharedcode.Database

actual fun getDataBase(): Database? {
    // On Android Database is passed from the app
    return null
}

//fun createDatabase(driver: SqlDriver): Database {
//    //val coordinateAdapter = object : ColumnAdapter<Coordinate, String> {
//    //    override fun decode(databaseValue: String): Coordinate {
//    //        val split = databaseValue.split(":")
//    //        return Coordinate(split[0].toFloat(), split[1].toFloat())
//    //    }
////
//    //    override fun encode(value: Coordinate): String {
//    //        return "${value.lat}:${value.lon}"
//    //    }
//    //}
//
//    return Database(
//        driver/*,
//        LocationModel.Adapter(
//            coordinateAdapter = coordinateAdapter
//        )*/
//    )
//}
