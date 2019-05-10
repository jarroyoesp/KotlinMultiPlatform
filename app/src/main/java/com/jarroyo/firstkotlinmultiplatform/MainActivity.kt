package com.jarroyo.firstkotlinmultiplatform

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        // INSERT LOCATION ON DATABASE
        val weatherRepository = LocationRepository(locationDao)
        GlobalScope.launch(Main) {
            weatherRepository.insertLocation()
        }


        // GET LOCATION FROM DATABASE
        GlobalScope.launch(Main) {
            val locationList = weatherRepository.getLocationList()
            Toast.makeText(applicationContext, "DB: ${locationList.size}", Toast.LENGTH_SHORT).show()
        }


    }

    private fun showData(data: String) {
        activity_main_tv.text = data
    }
}
