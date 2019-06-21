package com.jarroyo.firstkotlinmultiplatform.app.di.module

import com.jarroyo.firstkotlinmultiplatform.source.disk.DbArgs
import com.jarroyo.firstkotlinmultiplatform.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDataBaseArguments(appContext: App): DbArgs {
        return DbArgs(appContext)
    }
}
