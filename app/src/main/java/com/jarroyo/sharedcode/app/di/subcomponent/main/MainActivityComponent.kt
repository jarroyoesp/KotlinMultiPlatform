package com.jarroyo.sharedcode.app.di.subcomponent.main

import com.jarroyo.sharedcode.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun injectTo(activity: MainActivity)
}