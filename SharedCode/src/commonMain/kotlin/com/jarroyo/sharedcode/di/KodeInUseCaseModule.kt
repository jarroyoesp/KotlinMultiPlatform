package com.jarroyo.sharedcode.di

import com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList.GetWeatherListUseCase
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider

val KodeInUseCaseModule = Kodein.Module("KodeInUseCaseModule") {
    import(KodeInRepositoryModule)
    bind<GetWeatherListUseCase>() with provider { GetWeatherListUseCase(instance()) }
}