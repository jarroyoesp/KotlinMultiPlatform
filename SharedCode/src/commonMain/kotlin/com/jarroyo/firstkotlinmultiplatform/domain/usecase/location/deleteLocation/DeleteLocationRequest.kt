package com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.deleteLocation

import com.jarroyo.kotlinmultiplatform.domain.model.Location
import com.jarroyo.kotlinmultiplatform.domain.usecase.base.BaseRequest

class DeleteLocationRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}