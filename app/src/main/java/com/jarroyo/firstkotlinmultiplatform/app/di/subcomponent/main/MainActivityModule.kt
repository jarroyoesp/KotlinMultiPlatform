package com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main

import com.jarroyo.firstkotlinmultiplatform.app.di.module.ActivityModule
import com.jarroyo.firstkotlinmultiplatform.ui.main.MainActivity
import dagger.Module

@Module
class MainActivityModule(activity: MainActivity) : ActivityModule(activity) {

}