package com.jarroyo.android.app.di.module

import com.jarroyo.android.app.navigator.Navigator
import com.jarroyo.android.ui.App
import com.jarroyo.sharedcode.source.disk.DbArgs
import dagger.Module
import dagger.Provides
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import javax.inject.Singleton

@Module
class ApplicationModule(val app: App) {
    @Provides @Singleton
    fun provideApp(): App = app

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator()

    val KodeInDBArgs = Kodein {
        bind<DbArgs>() with singleton { DbArgs(app) }
    }
}