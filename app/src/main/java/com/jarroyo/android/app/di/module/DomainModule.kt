package com.jarroyo.android.app.di.module

import com.jarroyo.sharedcode.di.InjectorCommon
import com.jarroyo.sharedcode.di.InjectorCommon.KodeInInjector
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList.GetWeatherListUseCase
import com.jarroyo.sharedcode.source.disk.DbArgs
import dagger.Module
import dagger.Provides
import org.kodein.di.generic.instance
import javax.inject.Singleton


@Module
class DomainModule {

    /**
     * LOCATION
     */


    @Provides
    @Singleton
    fun provideSaveLocationUseCase(dbArgs: DbArgs) = InjectorCommon.provideSaveLocationUseCase(dbArgs)

    @Provides
    @Singleton
    fun provideDeleteLocationUseCase(dbArgs: DbArgs) = InjectorCommon.provideDeleteLocationUseCase(dbArgs)


    @Provides
    @Singleton
    fun provideGetLocationMPPListUseCase(dbArgs: DbArgs) = InjectorCommon.provideGetLocationMPPUseCase(dbArgs)

    /**
     * WEATHER
     */
    /**
     * WEATHER
     */
    @Provides
    @Singleton
    fun provideGetWeatherUseCase(): GetWeatherByNameUseCase {
        val getWeatherByNameUseCase by KodeInInjector.instance<GetWeatherByNameUseCase>()
        return getWeatherByNameUseCase
    }

    @Provides
    @Singleton
    fun provideGetWeatherListUseCase(): GetWeatherListUseCase {
        val getWeatherListUseCase by KodeInInjector.instance<GetWeatherListUseCase>()
        return getWeatherListUseCase
    }
}