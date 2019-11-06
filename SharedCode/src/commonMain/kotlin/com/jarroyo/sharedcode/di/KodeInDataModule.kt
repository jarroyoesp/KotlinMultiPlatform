package com.jarroyo.sharedcode.di

import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

val KodeInDataModule = Kodein.Module("KodeInDataModule") {
    bind<WeatherApi>() with singleton { WeatherApi() }
}