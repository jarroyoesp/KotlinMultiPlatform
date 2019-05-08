package com.jarroyo.firstkotlinmultiplatform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jarroyo.kotlinmultiplatform.requestData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //activity_main_tv.text = createApplicationScreenMessage()
        requestData(success = {
            GlobalScope.launch(Main) {
                showData(it.toString())
            }

        }, failure = {
            GlobalScope.launch(Main) {
                activity_main_tv.text = it?.message
            }
        })
    }

    private fun showData(data: String) {
        activity_main_tv.text = data
    }
}
