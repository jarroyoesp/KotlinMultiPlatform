package com.jarroyo.android.app.di.subcomponent.main

import com.jarroyo.android.app.di.module.ActivityModule
import com.jarroyo.android.ui.main.MainActivity
import dagger.Module

@Module
class MainActivityModule(activity: MainActivity) : ActivityModule(activity) {

}