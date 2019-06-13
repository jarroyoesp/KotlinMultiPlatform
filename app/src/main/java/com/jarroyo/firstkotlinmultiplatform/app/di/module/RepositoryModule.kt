package com.jarroyo.firstkotlinmultiplatform.app.di.module

import com.jarroyo.firstkotlinmultiplatform.Database
import com.jarroyo.kotlinmultiplatform.repository.LocationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLocationRepository(
        database: Database
    ) = LocationRepository(database)

}