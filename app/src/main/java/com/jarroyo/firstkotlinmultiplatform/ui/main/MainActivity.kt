package com.jarroyo.firstkotlinmultiplatform.ui.main

import android.os.Bundle
import android.widget.Toast
import com.jarroyo.firstkotlinmultiplatform.R
import com.jarroyo.firstkotlinmultiplatform.app.di.component.ApplicationComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.MainActivityModule
import com.jarroyo.firstkotlinmultiplatform.ui.base.BaseActivity
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.requestData
import com.regin.startmultiplatform.LocationRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity() {
    override var layoutId = R.layout.activity_main

    @Inject
    lateinit var locationRepository: LocationRepository

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MainActivityModule(this)).injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configView()

        // REQUEST DATA TO SERVICE -> INTERNET REQUIRED
        requestData(success = {
            GlobalScope.launch(Main) {
                showData(it.toString())
            }

        }, failure = {
            GlobalScope.launch(Main) {
                activity_main_tv.text = it?.message
            }
        })

        // GET LOCATION FROM DATABASE
        GlobalScope.launch(Main) {
            val locationList = locationRepository.getLocationList()
            Toast.makeText(applicationContext, "DB: ${locationList.size}", Toast.LENGTH_SHORT).show()
        }


    }

    private fun configView() {
        activity_main_button_add.setOnClickListener {
            if (activity_main_et_location.text.isNotEmpty()) {

                // INSERT LOCATION ON DATABASE
                GlobalScope.launch(Main) {
                    locationRepository.insertLocation(Location(activity_main_et_location.text.toString()))
                }
            }
        }
    }

    private fun showData(data: String) {
        activity_main_tv.text = data
    }
}
