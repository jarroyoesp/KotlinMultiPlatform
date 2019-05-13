package com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.deleteLocation

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.base.BaseRequest
import com.jarroyo.kotlinmultiplatform.domain.model.Location

class DeleteLocationRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}