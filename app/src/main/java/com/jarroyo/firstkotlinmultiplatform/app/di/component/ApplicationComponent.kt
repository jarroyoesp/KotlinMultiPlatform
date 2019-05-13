package com.jarroyo.firstkotlinmultiplatform.app.di.component

import com.jarroyo.firstkotlinmultiplatform.app.di.module.*
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.MainActivityComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.MainActivityModule
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.homeFragment.HomeFragmentComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.homeFragment.HomeFragmentModule
import com.jarroyo.firstkotlinmultiplatform.app.di.viewModel.ViewModelFactoryModule
import com.jarroyo.firstkotlinmultiplatform.app.di.viewModel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        DomainModule::class,
        RepositoryModule::class,
        DataModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        UtilsModule::class
    )
)
interface ApplicationComponent {
    /**
     * UI - ACTIVITY
     */
    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: HomeFragmentModule): HomeFragmentComponent

}