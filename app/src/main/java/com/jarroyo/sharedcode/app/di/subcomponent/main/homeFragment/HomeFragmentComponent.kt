package com.jarroyo.sharedcode.app.di.subcomponent.main.homeFragment

import com.jarroyo.sharedcode.ui.main.weatherListFragment.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(HomeFragmentModule::class))
interface HomeFragmentComponent {
    fun injectTo(fragment: HomeFragment)
}