package com.jarroyo.firstkotlinmultiplatform.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jarroyo.firstkotlinmultiplatform.R
import com.jarroyo.firstkotlinmultiplatform.app.di.component.ApplicationComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.MainActivityModule
import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.firstkotlinmultiplatform.ui.base.BaseActivity
import com.jarroyo.firstkotlinmultiplatform.ui.main.adapter.LocationListRVAdapter
import com.jarroyo.firstkotlinmultiplatform.ui.main.weatherListFragment.HomeFragment
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.LocationViewModel
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.ErrorGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.GetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.LoadingGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.SuccessGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.saveLocation.ErrorSaveLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.saveLocation.LoadingSaveLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.saveLocation.SaveLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.saveLocation.SuccessSaveLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.WeatherViewModel
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.ErrorGetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.GetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.LoadingGetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.SuccessGetWeatherByLocationState
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.CurrentWeather
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.repository.LocationRepository
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HomeFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    override var layoutId = R.layout.activity_main

    @Inject
    lateinit var locationRepository: LocationRepository


    // View model
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var locationviewModel: LocationViewModel
    private lateinit var weatherViewModel: WeatherViewModel

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mRvAdapter: LocationListRVAdapter? = null

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MainActivityModule(this)).injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configView()
        configObserver()

        getLocationListFromViewModel()
    }

    private fun configView() {

        configRecyclerView()

        activity_main_button_add.setOnClickListener {
            if (activity_main_et_location.text.isNotEmpty()) {
                locationviewModel.saveLocation(Location(activity_main_et_location.text.toString()))
            }
        }
    }

    fun configRecyclerView() {
        mLayoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
        activity_main_rv_location.layoutManager = mLayoutManager

        mRvAdapter = LocationListRVAdapter(
            listenerLocationClicked = {

            },
            listenerAddLocationClicked = {
                //showDialogAddLocation()
            }, listenerDeleteLocationClicked = {
                deleteLocation(Location(it.locationModel.city_name))
            }
        )

        activity_main_rv_location.adapter = mRvAdapter
    }


    private fun configObserver() {
        ///Observer
        locationviewModel = ViewModelProviders.of(this, viewModelFactory).get(LocationViewModel::class.java)
        observeLocationListViewModel()

        weatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        observeWeatherViewModel()
    }

    private fun getLocationListFromViewModel() {
        locationviewModel.getLocationList()
    }

    private fun getWeatherByLocation(location: Location) {
        weatherViewModel.getWeatherByLocation(location)
    }

    private fun deleteLocation(location: Location) {
        locationviewModel.deleteLocation(location)
    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/

    /** LOCATION OBSERVER **/
    private fun observeLocationListViewModel() {
        locationviewModel.getLocationListLiveData.observe(this, getLocationListStateObserver)
        locationviewModel.saveLocationListLiveData.observe(this, saveLocationListStateObserver)
    }

    private val getLocationListStateObserver = Observer<GetLocationListState> { state ->
        state?.let {
            when (state) {
                is SuccessGetLocationListState -> {
                    //hideLoading()
                    val success = it.response as Response.Success<List<LocationModel>>
                    showLocationList(success.data)
                }
                is LoadingGetLocationListState -> {
                    //showLoading()
                }
                is ErrorGetLocationListState -> {
                    //hideLoading()
                    //showError((it as ErrorCurrentWeatherState))
                }
            }
        }
    }

    private val saveLocationListStateObserver = Observer<SaveLocationState> { state ->
        state?.let {
            when (state) {
                is SuccessSaveLocationState -> {
                    //hideLoading()
                    val success = it.response as Response.Success<List<LocationModel>>
                    showLocationList(success.data)
                }
                is LoadingSaveLocationState -> {
                    //showLoading()
                }
                is ErrorSaveLocationState -> {
                    //hideLoading()
                    //showError((it as ErrorCurrentWeatherState))
                }
            }
        }
    }

    private fun showLocationList(locationList: List<LocationModel>) {
        mRvAdapter?.setLocationList(locationList)
        mRvAdapter?.notifyDataSetChanged()

        if (locationList.isNotEmpty()) {
            getWeatherByLocation(Location(locationList.get(0).city_name))
        }
    }

    /** WEATHER OBSERVER **/
    private fun observeWeatherViewModel() {
        weatherViewModel.getWeatherByLocationLiveData.observe(this, getWeatherByLocationStateObserver)
    }

    private val getWeatherByLocationStateObserver = Observer<GetWeatherByLocationState> { state ->
        state?.let {
            when (state) {
                is SuccessGetWeatherByLocationState -> {
                    //hideLoading()
                    val success = it.response as Response.Success<CurrentWeather>
                    showWeather(success.data)
                }
                is LoadingGetWeatherByLocationState -> {
                    //showLoading()
                }
                is ErrorGetWeatherByLocationState -> {
                    //hideLoading()
                    //showError((it as ErrorCurrentWeatherState))
                }
            }
        }
    }


    private fun showWeather(currentWeather: CurrentWeather) {
        activity_main_tv.text = currentWeather.toString()
    }
}
