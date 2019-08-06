package com.jarroyo.sharedcode.app.di.subcomponent.main

import com.jarroyo.sharedcode.app.di.module.ActivityModule
import com.jarroyo.sharedcode.ui.main.MainActivity
import dagger.Module

@Module
class MainActivityModule(activity: MainActivity) : ActivityModule(activity) {

}