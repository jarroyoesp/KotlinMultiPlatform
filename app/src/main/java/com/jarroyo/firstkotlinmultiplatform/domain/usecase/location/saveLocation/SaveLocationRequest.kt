package com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.base.BaseRequest
import com.jarroyo.kotlinmultiplatform.domain.model.Location

class SaveLocationRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}