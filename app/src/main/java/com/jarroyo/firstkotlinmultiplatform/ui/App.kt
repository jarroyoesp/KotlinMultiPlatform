package com.jarroyo.firstkotlinmultiplatform.ui

import androidx.multidex.MultiDexApplication
import com.jarroyo.firstkotlinmultiplatform.app.di.component.ApplicationComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.component.DaggerApplicationComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.module.ApplicationModule

open class App : MultiDexApplication() {

    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        graph = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}