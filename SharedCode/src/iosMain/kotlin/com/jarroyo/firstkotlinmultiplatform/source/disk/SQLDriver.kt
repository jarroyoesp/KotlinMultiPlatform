package com.jarroyo.firstkotlinmultiplatform.source.disk

import com.squareup.sqldelight.db.SqlDriver
import com.jarroyo.firstkotlinmultiplatform.Database
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver

actual class DbArgs(
)

actual fun getSqlDriver(dbArgs: DbArgs): SqlDriver {
    val driver: SqlDriver = NativeSqliteDriver(Database.Schema, "test.db")
    return driver
}