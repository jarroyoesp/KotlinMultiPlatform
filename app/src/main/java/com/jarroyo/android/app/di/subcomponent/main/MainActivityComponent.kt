package com.jarroyo.android.app.di.subcomponent.main

import com.jarroyo.android.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun injectTo(activity: MainActivity)
}