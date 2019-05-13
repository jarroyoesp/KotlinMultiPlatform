package com.jarroyo.firstkotlinmultiplatform.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jarroyo.firstkotlinmultiplatform.R
import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import kotlinx.android.synthetic.main.item_rv_location.view.*

class LocationListRVAdapter(
    private var mWeatherLocationList: List<LocationModel>? = listOf<LocationModel>(),
    private val listenerLocationClicked: (ItemAccount) -> Unit,
    private val listenerDeleteLocationClicked: (ItemAccount) -> Unit,
    private val listenerAddLocationClicked: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // CONSTANTS
    // ---------
    companion object {
        val TYPE_LOCATION = 0
        val TYPE_ADD_LOCATION = 1
    }

    override fun getItemCount(): Int {
        if (mWeatherLocationList != null) {
            return mWeatherLocationList?.size!!
        }
        return 0
    }

    override fun getItemViewType(position: Int): Int {
        if (mWeatherLocationList != null && position < mWeatherLocationList?.size!!) {
            return TYPE_LOCATION
        } else {
            return TYPE_ADD_LOCATION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_location, parent, false)
        return LocationViewHolder(itemView)
        /*when (viewType) {
            TYPE_LOCATION -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_location, parent, false)
                return LocationViewHolder(itemView)
            }

            TYPE_ADD_LOCATION -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_add_location, parent, false)
                return AddLocationViewHolder(itemView)
            }

            else -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_account, parent, false)
                return LocationViewHolder(itemView)
            }
        }*/
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mWeatherLocation = mWeatherLocationList?.get(position)
        val accountHolder = holder as LocationViewHolder
        accountHolder.bind(mWeatherLocation!!, position, listenerLocationClicked, listenerDeleteLocationClicked)
        /*when (holder.itemViewType) {
            TYPE_LOCATION -> {
                val mWeatherLocation = mWeatherLocationList?.get(position)
                val accountHolder = holder as LocationViewHolder
                accountHolder.bind(mWeatherLocation!!, position, listenerLocationClicked, listenerDeleteLocationClicked)
            }

            TYPE_ADD_LOCATION -> {
                val addLocationHolder = holder as AddLocationViewHolder
                addLocationHolder.bind(listenerAddLocationClicked)
            }
        }*/

    }

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(locationModel: LocationModel, position: Int, listener: (ItemAccount) -> Unit, listenerDeleted: (ItemAccount) -> Unit) = with(itemView) {
            item_rv_account_tv_title.text = locationModel.city_name

            // Delete location
            item_rv_account_iv_delete.setOnClickListener{
                listenerDeleted(ItemAccount(position, locationModel))
            }

            setOnClickListener {
                listener(ItemAccount(position, locationModel))
            }
        }
    }

    class AddLocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(listener: () -> Unit) = with(itemView) {


            setOnClickListener {
                listener()
            }
        }
    }

    fun setLocationList(locationList: List<LocationModel>) {
        mWeatherLocationList = locationList
    }
}

data class ItemAccount(val position: Int, val locationModel: LocationModel)