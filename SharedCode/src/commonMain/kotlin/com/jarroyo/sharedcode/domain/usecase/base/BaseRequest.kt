package com.jarroyo.sharedcode.domain.usecase.base

interface BaseRequest {
    fun validate(): Boolean
}
