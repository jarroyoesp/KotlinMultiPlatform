package com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.homeFragment

import com.jarroyo.firstkotlinmultiplatform.ui.main.weatherListFragment.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(HomeFragmentModule::class))
interface HomeFragmentComponent {
    fun injectTo(fragment: HomeFragment)
}