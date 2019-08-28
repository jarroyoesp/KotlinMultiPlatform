package com.jarroyo.sharedcode.source.disk

import com.jarroyo.sharedcode.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver


actual class DbArgs(
)

actual fun getSqlDriver(dbArgs: DbArgs): SqlDriver? {
    // By default JdbcSqliteDriver create database in Memory. If you want to create a dataBase on your path add something
    // similar to:
    // jdbc:sqlite:/Users/javierarroyo/Projects/Pruebas/KotlinMultiplatform/First/JavaFxApp/database/database.db
    val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:/Users/javierarroyo/Projects/Pruebas/KotlinMultiplatform/First/JavaFxApp/database/database.db")
    try {
        Database.Schema.create(driver)
    } catch (e: Exception) {}

    return driver
}