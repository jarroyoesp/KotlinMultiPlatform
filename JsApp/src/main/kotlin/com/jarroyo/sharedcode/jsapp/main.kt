package org.example.kotlin.multiplatform.web

import com.jarroyo.sharedcode.jsapp.app
import kotlinext.js.require
//import org.example.kotlin.multiplatform.data.NetworkDataSource
//import org.example.kotlin.multiplatform.repository.NetworkRepository
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    val globalState = object {
        //val networkRepository = makeRepository()
    }

    require("main.css")

    window.onload = {
        render(document.getElementById("content")) {
            app {
                attrs {
                    //networkRepository = globalState.networkRepository
                }
            }
        }
    }
}

//private fun makeRepository(): NetworkRepository =
//    NetworkRepository(
//        dataSource = NetworkDataSource()
//    )
