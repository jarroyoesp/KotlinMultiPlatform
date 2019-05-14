package com.jarroyo.firstkotlinmultiplatform.ui.main.weatherListFragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.OnClick
import com.jarroyo.firstkotlinmultiplatform.R
import com.jarroyo.firstkotlinmultiplatform.app.di.component.ApplicationComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.main.homeFragment.HomeFragmentModule
import com.jarroyo.firstkotlinmultiplatform.app.navigator.Navigator
import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.firstkotlinmultiplatform.ui.base.BaseFragment
import com.jarroyo.firstkotlinmultiplatform.ui.main.weatherListFragment.adapter.HomeListRVAdapter
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.LocationViewModel
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.ErrorGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.GetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.LoadingGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.SuccessGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.WeatherViewModel
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.ErrorGetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.GetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.LoadingGetWeatherByLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.weather.getWeatherByLocation.SuccessGetWeatherByLocationState
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.CurrentWeather
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    override var layoutId = R.layout.fragment_home

    private var listener: OnFragmentInteractionListener? = null

    // View model
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var locationViewModel: LocationViewModel

    private var isLoading = false

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mRvAdapter: HomeListRVAdapter? = null

    @Inject
    lateinit var navigator: Navigator

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HomeFragmentModule(this)).injectTo(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateView(inflater, container)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configRecyclerView()

        //Observer
        weatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        locationViewModel = ViewModelProviders.of(this, viewModelFactory).get(LocationViewModel::class.java)

        observeLocationListViewModel()
        observeWeatherListViewModel()

        getLocationList()
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    /**
     * CONFIG RV VIEW
     */
    fun configRecyclerView() {
        mLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
        fragment_home_rv.layoutManager = mLayoutManager

        mRvAdapter = HomeListRVAdapter(
            listenerAddLocationClicked = {

            },
            listenerWeatherClicked = {
                //navigator.toForecastActivity(it.weather.name)
            }
        )

        fragment_home_rv.adapter = mRvAdapter
        fragment_home_swipe_refresh_rv.setOnRefreshListener {
            getLocationList()
        }
    }

    /****************************************************************************
     * ONCLICK
     ***************************************************************************/
    @OnClick(R.id.fragment_home_button_retry)
    fun onClickRetry() {
        getLocationList()
    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    /** LOCATION LIST OBSERVER **/
    private fun observeLocationListViewModel() {
        locationViewModel.getLocationListLiveData.observe(this, locationListStateObserver)
    }

    private fun getLocationList() {
        locationViewModel.getLocationList()
    }

    private val locationListStateObserver = Observer<GetLocationListState> { state ->
        state?.let {
            when (state) {
                is SuccessGetLocationListState -> {
                    isLoading = false
                    hideLoading()
                    hideError()
                    val success = it.response as Response.Success<List<LocationModel>?>
                    getWeatherForLocationList(success.data)
                }
                is LoadingGetLocationListState -> {
                    isLoading = true
                    showLoading()
                    hideError()
                }
                is ErrorGetLocationListState -> {
                    isLoading = false
                    hideLoading()
                    //showError((it as ErrorCurrentWeatherState))
                }
            }
        }
    }


    private fun observeWeatherListViewModel() {
        weatherViewModel.getWeatherByLocationLiveData.observe(this, weatherListStateObserver)
    }

    private val weatherListStateObserver = Observer<GetWeatherByLocationState> { state ->
        state?.let {
            when (state) {
                is SuccessGetWeatherByLocationState -> {
                    isLoading = false
                    hideLoading()
                    hideError()
                    val success = it.response as Response.Success<CurrentWeather>
                    showInRVWeatherList(success.data)
                }
                is LoadingGetWeatherByLocationState -> {
                    isLoading = true
                    showLoading()
                    hideError()
                }
                is ErrorGetWeatherByLocationState -> {
                    isLoading = false
                    hideLoading()
                    //showError((it as ErrorCurrentWeatherState))
                }
            }
        }
    }


    /**
     * GET WEATHER LOCATION LIST
     */
    private fun getWeatherForLocationList(weatherLocationList: List<LocationModel>?){
        if (weatherLocationList != null && weatherLocationList.size > 0) {
            weatherViewModel.getWeatherByLocation(Location(weatherLocationList.get(0).city_name))
        } else {
            showEmptyLocationList()
        }
    }


    /**
     * SHOW CURRENT WEATHER
     */
    private fun showInRVWeatherList(currentWeather: CurrentWeather?){
        var weatherList = mutableListOf<CurrentWeather>()
        weatherList.add(currentWeather!!)
        mRvAdapter?.setWeatherList(weatherList)
        mRvAdapter?.notifyDataSetChanged()
    }

    /**
     * SHOW EMPTY LOCATIONS
     */
    private fun showEmptyLocationList() {
        fragment_home_layout_error.visibility = View.VISIBLE
        fragment_home_tv_status.text = getString(R.string.empty_location)
    }

    /**
     * SHOW LOADING
     */
    private fun showLoading(){
        fragment_home_loading.visibility = View.VISIBLE
        fragment_home_swipe_refresh_rv.isRefreshing = true
    }

    /**
     * HIDE LOADING
     */
    private fun hideLoading(){
        fragment_home_loading.visibility = View.GONE
        fragment_home_swipe_refresh_rv.isRefreshing = false
    }

    /**
     * SHOW ERROR
     */
    private fun showError(errorForecastState: ErrorGetWeatherByLocationState){
        fragment_home_layout_error.visibility = View.VISIBLE
        val error = errorForecastState.response as Response.Error
        fragment_home_tv_status.text = error.exception.message
    }

    /**
     * HIDE ERROR
     */
    private fun hideError(){
        fragment_home_layout_error.visibility = View.GONE
    }

}
