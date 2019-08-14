package com.jarroyo.sharedcode.source.disk

import com.squareup.sqldelight.db.SqlDriver


actual class DbArgs(
)

actual fun getSqlDriver(dbArgs: DbArgs): SqlDriver? {
    // By default JdbcSqliteDriver create database in Memory. If you want to create a dataBase on your path add something
    // similar to:
    // jdbc:sqlite:/Users/javierarroyo/Projects/Pruebas/KotlinMultiplatform/First/database/database.db
    //val driver: SqlDriver = SqlDriver
    //try {
    //    Database.Schema.create(driver)
    //} catch (e: Exception) {}

    return null
}