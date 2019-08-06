package com.jarroyo.sharedcode.domain.usecase.location.deleteLocation

import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.domain.usecase.base.BaseRequest

class DeleteLocationRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}