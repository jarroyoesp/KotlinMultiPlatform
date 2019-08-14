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

class Application : RComponent<ApplicationProps, RState>() {

    //lateinit var mPresenter: WeatherPresenter

    override fun RBuilder.render() {
        div(classes = "container") {
            div(classes = "header clearfix") {
                h3 { +"Kotlin Multiplatform Example" }
            }
            child(hello(), props = props)
        }

        //getData()
    }

    private fun getData() {
        GlobalScope.launch {
            val weatherApi: WeatherApi by kotlin.lazy { WeatherApi() }
            //WeatherRepository(weatherApi).getCurrentWeather(Location("Andorra"), success ={}, failure = {} )
        }
    }

    private fun initPresenter() {
       //mPresenter = InjectorCommon.provideWeatherPresenter()
       //mPresenter.attachView(this)
//
       // mPresenter.getCurrentWeather(Location("Andorra"))

    }

//    override fun onSuccessGetCurrentWeather(currentWeather: CurrentWeather) {
//        //Platform.runLater {
//        //    tempMaxLabel.text = "Temp. Max ${currentWeather.main?.temp_max.toString()} ºC"
//        //    tempMinLabel.text = "Temp. Min ${currentWeather.main?.temp_min.toString()} ºC"
//        //    descriptionLabel.text = "Description: ${currentWeather.weather?.get(0)?.description}"
//        //}
//    }
//
//    override fun onErrorGetCurrentWeather(throwable: Throwable) {
//    }
//
//    override fun showHideLoading(visible: Boolean) {
//    }
//
//    private fun showDialog(title: String, header: String = "", content: String = "") {
//       //Platform.runLater {
//       //    val alert = Alert(AlertType.WARNING)
//       //    alert.title = title
//       //    alert.headerText = header
//       //    alert.contentText = content
//
//       //    alert.showAndWait()
//       //}
//    }
//
//    private fun refreshLocationList(locationModelList: List<LocationModel>) {
//       // Platform.runLater {
//       //     var locationListParsed = arrayListOf<Location>()
//
//       //    for (locationModel in locationModelList) {
//       //        var location = Location(locationModel.city_name, locationModel.country ?: "Uknown")
//       //        locationListParsed.add(location)
//       //    }
//
//       //    if (locationListParsed.isNotEmpty()) {
//       //        val cityName = locationListParsed?.last().cityName
//
//       //        val array = arrayOfNulls<Location>(locationListParsed.size)
//       //        listView.items.clear()
//       //        listView.items.addAll(locationListParsed.toArray(array))
//       //        listView.refresh()
//       //    }
//
//       //}
//    }
}

fun hello() = functionalComponent<ApplicationProps> { props ->
    val (name, setName) = useState(null as String?)

    div {
        p { +if (name == null) "Search your weather" else "Hello, $name" }
        button(classes = "btn btn-primary") {
            +"Fetch"
            attrs {
                onClickFunction = {
                    GlobalScope.launch {
                        console.log(props)
                        //val name = props.networkRepository.getGreeting("web")
                        //setName(name.message)
                    }
                }
            }
        }
    }
}

external interface ApplicationProps : RProps {
    //var networkRepository: NetworkRepository
}

fun RBuilder.app(handler: RHandler<ApplicationProps>) = child(Application::class, handler)


