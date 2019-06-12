package com.jarroyo.firstkotlinmultiplatform.app.di.module

import com.jarroyo.firstkotlinmultiplatform.BuildConfig
import com.jarroyo.firstkotlinmultiplatform.Database
import com.jarroyo.firstkotlinmultiplatform.ui.App
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDataBase(appContext: App): Database {
        val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, appContext, BuildConfig.DATABASE_NAME)
        return Database(driver)
    }
}
