package com.jarroyo.sharedcode.di

import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import com.jarroyo.sharedcode.domain.usecase.location.deleteLocation.DeleteLocationUseCase
import com.jarroyo.sharedcode.domain.usecase.location.getLocationList.GetLocationMPPListUseCase
import com.jarroyo.sharedcode.domain.usecase.location.saveLocation.SaveLocationUseCase
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList.GetWeatherListUseCase
import com.jarroyo.sharedcode.presentation.ProfilePresenter
import com.jarroyo.sharedcode.presentation.WeatherPresenter
import com.jarroyo.sharedcode.repository.LocationRepository
import com.jarroyo.sharedcode.repository.WeatherRepository
import com.jarroyo.sharedcode.source.disk.DbArgs
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InjectorCommon {

    /**
     * WEATHER
     */
    private val weatherApi: WeatherApi by lazy { WeatherApi() }


    private val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(weatherApi)
    }

    fun provideGetWeatherListUseCase(): GetWeatherListUseCase {
        return GetWeatherListUseCase(weatherRepository)
    }

    fun provideGetWeatherUseCase(): GetWeatherByNameUseCase {
        return GetWeatherByNameUseCase(weatherRepository)
    }

    /**
     * LOCATION
     */
    lateinit var mDbArgs: DbArgs

    private val locationRepository: LocationRepository by lazy {
        LocationRepository(mDbArgs)
    }

    fun provideGetLocationMPPUseCase(dbArgs: DbArgs): GetLocationMPPListUseCase {
        mDbArgs = dbArgs
        return GetLocationMPPListUseCase(locationRepository)
    }

    fun provideDeleteLocationUseCase(dbArgs: DbArgs): DeleteLocationUseCase {
        mDbArgs = dbArgs
        return DeleteLocationUseCase(locationRepository)
    }

    fun provideSaveLocationUseCase(dbArgs: DbArgs): SaveLocationUseCase {
        mDbArgs = dbArgs
        return SaveLocationUseCase(locationRepository)
    }

    /**
     * PRESENTER
     */
    fun provideProfilePresenter(dbArgs: DbArgs): ProfilePresenter {
        return ProfilePresenter(provideGetLocationMPPUseCase(dbArgs), provideGetWeatherUseCase(), provideSaveLocationUseCase(dbArgs))
    }

    fun provideWeatherPresenter(): WeatherPresenter {
        return WeatherPresenter(provideGetWeatherUseCase())
    }

    val KodeInInjector = Kodein {
        //import(KodeInDataModule)
        //import(KodeInRepositoryModule)
        //import(KodeInUseCaseModule)

        bind<WeatherApi>() with singleton { WeatherApi() }
        bind<WeatherRepository>() with provider { WeatherRepository(instance()) }
        bind<GetWeatherListUseCase>() with provider { GetWeatherListUseCase(instance()) }
        bind<GetWeatherByNameUseCase>() with provider { GetWeatherByNameUseCase(instance()) }
    }

}
