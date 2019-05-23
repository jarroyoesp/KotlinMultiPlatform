package com.jarroyo.kotlinmultiplatform

import com.squareup.sqldelight.db.SqlDriver
import com.jarroyo.firstkotlinmultiplatform.Database
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver

actual fun getDataBase(): Database? {
    val driver: SqlDriver = NativeSqliteDriver(Database.Schema, "test.db")
    return Database(driver)
}