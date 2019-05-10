package com.jarroyo.firstkotlinmultiplatform

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.requestData
import com.jarroyo.kotlinmultiplatform.source.disk.dao.LocationDao
import com.regin.startmultiplatform.LocationRepository
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mWeatherRepository: LocationRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        // ACCESS TO LOCAL DATA -> SQLDELIGHT
        val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, this, "test.db")
        val locationDao = LocationDao(Database(driver))


        mWeatherRepository = LocationRepository(locationDao)

        // GET LOCATION FROM DATABASE
        GlobalScope.launch(Main) {
            val locationList = mWeatherRepository.getLocationList()
            Toast.makeText(applicationContext, "DB: ${locationList.size}", Toast.LENGTH_SHORT).show()
        }


    }

    private fun configView() {
        activity_main_button_add.setOnClickListener {
            if (activity_main_et_location.text.isNotEmpty()) {

                // INSERT LOCATION ON DATABASE
                GlobalScope.launch(Main) {
                    mWeatherRepository.insertLocation(Location(activity_main_et_location.text.toString()))
                }
            }
        }
    }

    private fun showData(data: String) {
        activity_main_tv.text = data
    }
}
