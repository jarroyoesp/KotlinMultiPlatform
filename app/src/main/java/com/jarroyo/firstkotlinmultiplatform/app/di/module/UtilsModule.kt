package com.jarroyo.firstkotlinmultiplatform.app.di.module

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class UtilsModule {
    /*@Provides
    @Singleton
    fun provideNetworkSystem(app: App) =
            NetworkSystem(app) as NetworkSystemAbstract*/

    @Provides
    @Singleton
    fun provideCorutineContext() =
        Dispatchers.Default as CoroutineContext

    /*@Provides
    @Singleton
    fun provideNotificationManager(app: App)
            = NotificationTDDManager(app)*/
}