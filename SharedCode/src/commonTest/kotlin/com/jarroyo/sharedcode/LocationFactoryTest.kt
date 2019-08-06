package com.jarroyo.sharedcode

import com.jarroyo.sharedcode.data.LocationModel

object LocationFactoryTest {

    fun createLocationModelListTest(): List<LocationModel> {
        var locationList = mutableListOf<LocationModel>()

        val location1 = LocationModel.Impl(1,"Andorra", "Spain")
        locationList.add(location1)

        val location2 = LocationModel.Impl(2,"Zaragoza", "Spain")
        locationList.add(location2)

        return locationList

    }
}