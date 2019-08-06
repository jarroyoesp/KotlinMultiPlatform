package com.jarroyo.sharedcode.domain.usecase.location.saveLocation

import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.domain.usecase.base.BaseRequest

class SaveLocationRequest(var location: Location) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}