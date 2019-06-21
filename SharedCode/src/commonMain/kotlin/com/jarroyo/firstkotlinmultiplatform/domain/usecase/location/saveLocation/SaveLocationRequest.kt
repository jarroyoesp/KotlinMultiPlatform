package com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation

import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.domain.usecase.base.BaseRequest

class SaveLocationRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}