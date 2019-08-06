package com.jarroyo.sharedcode.app.di.module

import com.jarroyo.sharedcode.source.disk.DbArgs
import com.jarroyo.sharedcode.ui.App
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
