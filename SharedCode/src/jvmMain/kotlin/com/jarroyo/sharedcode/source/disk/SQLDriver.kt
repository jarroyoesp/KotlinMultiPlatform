package com.jarroyo.sharedcode.source.disk

import com.jarroyo.sharedcode.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver


actual class DbArgs(
)

actual fun getSqlDriver(dbArgs: DbArgs): SqlDriver {
    val driver: SqlDriver = JdbcSqliteDriver()
    return driver
}