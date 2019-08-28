package com.jarroyo.sharedcode.jsapp

//import org.example.kotlin.multiplatform.repository.NetworkRepository
import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.div
import react.dom.h3
import react.dom.p
import com.jarroyo.sharedcode.di.InjectorCommon
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.presentation.WeatherPresenter
import com.jarroyo.sharedcode.presentation.WeatherView
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLParagraphElement
import kotlin.browser.document

class Application : RComponent<ApplicationProps, RState>(), WeatherView {

    lateinit var mPresenter: WeatherPresenter

    override fun RBuilder.render() {
        initPresenter()
        configView()
    }

    private fun initPresenter() {
        mPresenter = InjectorCommon.provideWeatherPresenter()
        mPresenter.attachView(this)
    }

    private fun configView(){

        val buttonGetWeather = document.getElementById("buttonGetWeather") as HTMLButtonElement
        buttonGetWeather.onclick = {
            val locationTextArea = document.getElementById("locationTextArea") as HTMLInputElement
            val locationName = locationTextArea.value

            if (!locationName.isNullOrEmpty()) {
                mPresenter.getCurrentWeather(Location(locationName))
            } else {
                val descriptionLabel = document.getElementById("descriptionLabel") as HTMLParagraphElement
                descriptionLabel.textContent = "Enter a location to GetWeather"
            }
        }
    }

    override fun onSuccessGetCurrentWeather(currentWeather: CurrentWeather) {
        console.log(currentWeather)
        val tempMaxLabel = document.getElementById("tempMaxLabel") as HTMLParagraphElement
        val tempMinLabel = document.getElementById("tempMinLabel") as HTMLParagraphElement
        val descriptionLabel = document.getElementById("descriptionLabel") as HTMLParagraphElement

        tempMaxLabel.textContent  =  "Temp. Max ${currentWeather.main?.temp_max.toString()} ºC"
        tempMinLabel.textContent = "Temp. Min ${currentWeather.main?.temp_min.toString()} ºC"
        descriptionLabel.textContent = "Description: ${currentWeather.weather?.get(0)?.description}"
    }

    override fun onErrorGetCurrentWeather(throwable: Throwable) {
    }

    override fun showHideLoading(visible: Boolean) {
    }

    private fun showDialog(title: String, header: String = "", content: String = "") {
    }
}

external interface ApplicationProps : RProps {
    //var networkRepository: NetworkRepository
}

fun RBuilder.app(handler: RHandler<ApplicationProps>) = child(Application::class, handler)


