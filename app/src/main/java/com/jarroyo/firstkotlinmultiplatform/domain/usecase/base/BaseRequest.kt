package com.jarroyo.firstkotlinmultiplatform.domain.usecase.base

interface BaseRequest {
    fun validate(): Boolean
}
