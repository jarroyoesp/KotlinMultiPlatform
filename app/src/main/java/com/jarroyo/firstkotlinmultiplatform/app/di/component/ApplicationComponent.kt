package com.jarroyo.firstkotlinmultiplatform.app.di.component

import com.jarroyo.firstkotlinmultiplatform.app.di.module.ApplicationModule
import com.jarroyo.firstkotlinmultiplatform.app.di.module.DataModule
import com.jarroyo.firstkotlinmultiplatform.app.di.module.RepositoryModule
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.MainActivityComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.MainActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        DataModule::class,
        RepositoryModule::class/*,
        UtilsModule::class,

        DomainModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class*/
    )
)
interface ApplicationComponent {
    /**
     * UI - ACTIVITY
     */
    fun plus(module: MainActivityModule): MainActivityComponent

}