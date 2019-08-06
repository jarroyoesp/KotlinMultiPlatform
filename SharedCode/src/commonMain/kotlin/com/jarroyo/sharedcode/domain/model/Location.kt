package com.jarroyo.sharedcode.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(val cityName: String, val country: String = "")