package com.jarroyo.sharedcode.app.di.module

import com.jarroyo.sharedcode.app.navigator.Navigator
import com.jarroyo.sharedcode.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val app: App) {
    @Provides @Singleton
    fun provideApp(): App = app

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator()
}