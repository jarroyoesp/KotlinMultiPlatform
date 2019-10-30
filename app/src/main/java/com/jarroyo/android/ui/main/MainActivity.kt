package com.jarroyo.android.ui.main

import android.net.Uri
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jarroyo.android.R
import com.jarroyo.android.app.di.component.ApplicationComponent
import com.jarroyo.android.app.di.subcomponent.main.MainActivityModule
import com.jarroyo.android.ui.base.BaseActivity
import com.jarroyo.android.ui.main.weatherListFragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), HomeFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    override var layoutId = R.layout.activity_main

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MainActivityModule(this)).injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity_home_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    /**
     * NAVIGATION BOTTOM
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                cleanFragmentBackStack()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                if (activity_home_navigation.getSelectedItemId() != R.id.navigation_account) {
                    navigator.addAccountFragment(R.id.activity_main_layout)
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
