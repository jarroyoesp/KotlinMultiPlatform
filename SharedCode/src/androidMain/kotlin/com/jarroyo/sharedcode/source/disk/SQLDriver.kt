package com.jarroyo.sharedcode.source.disk

import android.content.Context
import com.jarroyo.sharedcode.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver


actual class DbArgs(
    var context: Context
)

actual fun getSqlDriver(dbArgs: DbArgs): SqlDriver? {
    val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, dbArgs.context, "test.db")
    return driver
}