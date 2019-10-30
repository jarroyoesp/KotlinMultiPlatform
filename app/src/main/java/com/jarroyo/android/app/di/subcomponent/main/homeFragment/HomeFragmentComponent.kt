package com.jarroyo.android.app.di.subcomponent.main.homeFragment

import com.jarroyo.android.ui.main.weatherListFragment.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(HomeFragmentModule::class))
interface HomeFragmentComponent {
    fun injectTo(fragment: HomeFragment)
}