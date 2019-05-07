package com.jarroyo.firstkotlinmultiplatform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jarroyo.kotlinmultiplatform.createApplicationScreenMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_tv.text = createApplicationScreenMessage()
    }
}
