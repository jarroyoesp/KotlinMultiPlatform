package com.jarroyo.sharedcode.source.disk

import com.jarroyo.sharedcode.Database
import com.squareup.sqldelight.db.SqlDriver

expect class DbArgs

expect fun getSqlDriver(dbArgs: DbArgs): SqlDriver?

object DatabaseCreator {
    fun getDataBase(dbArgs: DbArgs): Database? {
        val sqlDriver  = getSqlDriver(dbArgs)
        if (sqlDriver != null) {
            return Database(sqlDriver)
        } else {
            return null
        }
    }
}