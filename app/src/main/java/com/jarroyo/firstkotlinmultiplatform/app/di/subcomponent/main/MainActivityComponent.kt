package com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main

import com.jarroyo.firstkotlinmultiplatform.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun injectTo(activity: MainActivity)
}