package com.jarroyo.sharedcode.di

import com.jarroyo.sharedcode.repository.WeatherRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider

val KodeInRepositoryModule = Kodein.Module("KodeInRepositoryModule") {
    import(KodeInDataModule)
    bind<WeatherRepository>() with provider { WeatherRepository(instance()) }
}