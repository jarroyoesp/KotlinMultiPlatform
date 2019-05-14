package com.jarroyo.kotlinmultiplatform.domain.usecase.base

interface BaseRequest {
    fun validate(): Boolean
}
