package com.jarroyo.kotlinmultiplatform

import com.jarroyo.sharedcode.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual fun getDataBase(): Database? {
    val driver: SqlDriver = JdbcSqliteDriver()
    return Database(driver)
}

