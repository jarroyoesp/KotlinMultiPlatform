package com.jarroyo.firstkotlinmultiplatform.app.di.module

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.getLocationList.GetLocationListUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation.SaveLocationUseCase
import com.regin.startmultiplatform.LocationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule {

    /**
     * LOCATION
     */
    @Provides
    @Singleton
    fun provideGetLocationListUseCase(repository: LocationRepository) = GetLocationListUseCase(repository)


    @Provides
    @Singleton
    fun provideSaveLocationUseCase(repository: LocationRepository) = SaveLocationUseCase(repository)
}