package com.jarroyo.kotlinmultiplatform

import com.jarroyo.firstkotlinmultiplatform.Database

expect fun getDataBase(): Database

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
