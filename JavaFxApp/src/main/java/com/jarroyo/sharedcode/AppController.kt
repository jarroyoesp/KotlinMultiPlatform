package com.jarroyo.sharedcode
import com.jarroyo.sharedcode.data.LocationModel
import com.jarroyo.sharedcode.di.InjectorCommon
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.presentation.ProfilePresenter
import com.jarroyo.sharedcode.presentation.ProfileView
import com.jarroyo.sharedcode.source.disk.DbArgs
import javafx.application.Platform
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region

class AppController: ProfileView {


    lateinit var rootPane: HBox
    lateinit var detailView: ScrollPane
    lateinit var detailLabel: Label
    lateinit var listView: ListView<Location>

    lateinit var mPresenter: ProfilePresenter

    fun initialize() {
        adjustLayoutWidthToMax(listView, detailView)

        initPresenter()

        listView.setCellFactory {
            object : ListCell<Location>() {
                override fun updateItem(item: Location?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = item?.cityName
                }
            }
        }


        val location1 = Location("Andorra", "Spain")
        val location2 = Location("Zaragoza", "Spain")
        val location3 = Location("London", "England")
        val languageFacts = arrayOf(location1,location2, location3)
        listView.items.addAll(languageFacts)
        listView.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            detailLabel.text = newValue.cityName

            mPresenter.getCurrentWeather(Location(newValue.cityName, "Spain"))
        }

        // add an initial selection
        listView.selectionModel.select(0)
    }

    private fun adjustLayoutWidthToMax(vararg views: Region) {
        views.forEach {
            it.maxWidth = 400.0
            HBox.setHgrow(it, Priority.ALWAYS)
        }
    }

    private fun initPresenter() {
        var dbArgsPar =  DbArgs()
        mPresenter = InjectorCommon.provideProfilePresenter(dbArgsPar)
        mPresenter.attachView(this)

    }

    override fun onSuccessGetLocationList(locationList: List<LocationModel>) {

    }

    override fun onErrorGetLocationList(throwable: Throwable) {
    }

    override fun onSuccessGetCurrentWeather(currentWeather: CurrentWeather) {
        Platform.runLater {
            // Update UI here.
            detailLabel.text = currentWeather.toString()
        }
    }

    override fun onErrorGetCurrentWeather(throwable: Throwable) {
    }

    override fun onSuccessSaveLocation(locationList: List<LocationModel>) {
    }

    override fun onErrorSaveLocation(throwable: Throwable) {
    }

    override fun showHideLoading(visible: Boolean) {
    }

}
