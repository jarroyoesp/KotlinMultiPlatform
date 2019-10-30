package com.jarroyo.android.app.di.component

import com.jarroyo.android.app.di.module.ApplicationModule
import com.jarroyo.android.app.di.module.DataModule
import com.jarroyo.android.app.di.module.DomainModule
import com.jarroyo.android.app.di.module.UtilsModule
import com.jarroyo.android.app.di.subcomponent.account.AccountFragmentComponent
import com.jarroyo.android.app.di.subcomponent.account.AccountFragmentModule
import com.jarroyo.android.app.di.subcomponent.main.MainActivityComponent
import com.jarroyo.android.app.di.subcomponent.main.MainActivityModule
import com.jarroyo.android.app.di.subcomponent.main.homeFragment.HomeFragmentComponent
import com.jarroyo.android.app.di.subcomponent.main.homeFragment.HomeFragmentModule
import com.jarroyo.android.app.di.viewModel.ViewModelFactoryModule
import com.jarroyo.android.app.di.viewModel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        DomainModule::class,
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

    /**
     * FRAGMENT
     */
    fun plus(module: HomeFragmentModule): HomeFragmentComponent
    fun plus(module: AccountFragmentModule): AccountFragmentComponent

}