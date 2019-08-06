package com.jarroyo.sharedcode.ui

import androidx.multidex.MultiDexApplication
import com.jarroyo.sharedcode.app.di.component.ApplicationComponent
import com.jarroyo.sharedcode.app.di.component.DaggerApplicationComponent
import com.jarroyo.sharedcode.app.di.module.ApplicationModule

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