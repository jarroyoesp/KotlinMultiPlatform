package com.jarroyo.firstkotlinmultiplatform.source.disk

import com.jarroyo.firstkotlinmultiplatform.Database
import com.squareup.sqldelight.db.SqlDriver

expect class DbArgs

expect fun getSqlDriver(dbArgs: DbArgs): SqlDriver

object DatabaseCreator {
    fun getDataBase(dbArgs: DbArgs): Database {
        return Database(getSqlDriver(dbArgs))
    }
}