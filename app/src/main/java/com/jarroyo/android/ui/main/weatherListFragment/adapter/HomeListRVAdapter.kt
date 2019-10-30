package com.jarroyo.android.ui.main.weatherListFragment.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jarroyo.android.R
import com.jarroyo.android.ui.main.adapter.LocationListRVAdapter.Companion.TYPE_LOCATION
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import kotlinx.android.synthetic.main.item_rv_weather.view.*

class HomeListRVAdapter(
    private var mWeatherList: List<CurrentWeather>? = listOf<CurrentWeather>(),
    private val listenerWeatherClicked: (ItemWeather) -> Unit,
    private val listenerAddLocationClicked: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // CONSTANTS
    // ---------
    companion object {
        val TYPE_WEATHER = 0
        val TYPE_ADD_LOCATION = 1
    }

    override fun getItemCount(): Int {
        if (mWeatherList != null) {
            return mWeatherList?.size!!
        }
        return 0
    }

    override fun getItemViewType(position: Int): Int {
        if (mWeatherList != null && position < mWeatherList?.size!!) {
            return TYPE_WEATHER
        } else {
            return TYPE_ADD_LOCATION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_WEATHER -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_weather, parent, false)
                return WeatherViewHolder(itemView)
            }

            TYPE_ADD_LOCATION -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_add_location, parent, false)
                return AddLocationViewHolder(itemView)
            }

            else -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_weather, parent, false)
                return WeatherViewHolder(itemView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_LOCATION -> {
                val mWeather = mWeatherList?.get(position)
                val weatherHolder = holder as WeatherViewHolder
                weatherHolder.bind(mWeather!!, position, listenerWeatherClicked)
            }

            TYPE_ADD_LOCATION -> {
                val addLocationHolder = holder as AddLocationViewHolder
                addLocationHolder.bind(listenerAddLocationClicked)
            }
        }

    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: CurrentWeather, position: Int, listener: (ItemWeather) -> Unit) = with(itemView) {
            item_rv_weather_tv_city.text = weather.name

            // TEMP MAX/ MIN
            var tempMaxRounded = Math.round(weather.main?.temp_max!!)
            var tempMinRounded = Math.round(weather.main?.temp_min!!)
            item_rv_weather_tv_temp_max.text = "${context.getString(R.string.tempMax)}: ${tempMaxRounded.toString()} ºC"
            item_rv_weather_tv_temp_min.text = "${context.getString(R.string.tempMin)}: ${tempMinRounded.toString()} ºC"

            // Wind
            item_rv_weather_tv_wind.text = "${context.getString(R.string.wind)}: ${weather.wind?.speed} km/h"

            // TEMP BIG
            var tempRounded = Math.round(weather.main?.temp!!)
            item_rv_weather_tv_temp_big.text = "${tempRounded.toString()} ºC"

            // Sunrise
            val timeSunrise = DateUtils.formatDateTime(context, weather.sys?.sunrise!! * 1000L, DateUtils.FORMAT_SHOW_TIME)
            item_rv_weather_tv_sunrise.text = "${context.getString(R.string.sunrise)}: ${timeSunrise}"

            // Sunset
            val timeSunset = DateUtils.formatDateTime(context, weather.sys?.sunset!! * 1000L, DateUtils.FORMAT_SHOW_TIME)
            item_rv_weather_tv_sunset.text = "${context.getString(R.string.sunset)}: ${timeSunset}"

            // ICON
            //item_rv_weather_iv_avatar.loadUrl("${BuildConfig.OPEN_WEATHER_URL_ICON_BASE}${weather.weather?.first()?.icon}.png")
            setOnClickListener {
                listener(ItemWeather(position, weather, this))
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

    fun setWeatherList(weatherList: List<CurrentWeather>?) {
        mWeatherList = weatherList
    }
}

data class ItemWeather(val position: Int, val weather: CurrentWeather, val itemView: View)