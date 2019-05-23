package com.jarroyo.firstkotlinmultiplatform.ui.account.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jarroyo.firstkotlinmultiplatform.R
import com.jarroyo.firstkotlinmultiplatform.app.di.component.ApplicationComponent
import com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.account.AccountFragmentModule
import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.jarroyo.firstkotlinmultiplatform.ui.base.BaseFragment
import com.jarroyo.firstkotlinmultiplatform.ui.main.adapter.LocationListRVAdapter
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.LocationViewModel
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.ErrorGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.GetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.LoadingGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.getLocation.SuccessGetLocationListState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.saveLocation.ErrorSaveLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.saveLocation.LoadingSaveLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.saveLocation.SaveLocationState
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.saveLocation.SuccessSaveLocationState
import com.jarroyo.kotlinmultiplatform.domain.Response
import com.jarroyo.kotlinmultiplatform.domain.model.Location
import kotlinx.android.synthetic.main.fragment_account.*
import javax.inject.Inject


class AccountFragment : BaseFragment() {
    override var layoutId = R.layout.fragment_account

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var locationviewModel: LocationViewModel

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mRvAdapter: LocationListRVAdapter? = null

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AccountFragmentModule(this)).injectTo(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflateView(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configView()
        configObserver()

        getLocationListFromViewModel()
    }

    private fun configView() {

        configRecyclerView()

        fragment_account_button_add.setOnClickListener {
            if (fragment_account_et_location.text.isNotEmpty()) {
                locationviewModel.saveLocation(Location(fragment_account_et_location.text.toString()))
            }
        }
    }

    fun configRecyclerView() {
        mLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
        fragment_account_rv_location.layoutManager = mLayoutManager

        mRvAdapter = LocationListRVAdapter(
            listenerLocationClicked = {

            },
            listenerAddLocationClicked = {
                //showDialogAddLocation()
            }, listenerDeleteLocationClicked = {
                deleteLocation(Location(it.locationModel.city_name))
            }
        )

        fragment_account_rv_location.adapter = mRvAdapter
    }


    private fun configObserver() {
        ///Observer
        locationviewModel = ViewModelProviders.of(this, viewModelFactory).get(LocationViewModel::class.java)
        observeLocationListViewModel()
    }

    private fun getLocationListFromViewModel() {
        locationviewModel.getLocationList()
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
    }

    companion object{
        fun newInstance(): AccountFragment {
            return AccountFragment()
        }
    }
}
