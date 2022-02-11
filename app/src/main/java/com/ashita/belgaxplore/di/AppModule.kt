package com.ashita.belgaxplore.di

import com.ashita.belgaxplore.data.BelgaXploreApiService
import com.ashita.belgaxplore.domain.repository.LocationDetailsRepository
import com.ashita.belgaxplore.domain.repository.LocationDetailsRepositoryImpl
import com.ashita.belgaxplore.domain.repository.LocationsListRepository
import com.ashita.belgaxplore.domain.repository.LocationsListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBelgaExploreApi(): BelgaXploreApiService {
        return BelgaXploreApiService
    }

    @Provides
    @Singleton
    fun provideLocationsListRepository(api: BelgaXploreApiService): LocationsListRepository {
        return LocationsListRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLocationDetailsRepository(api: BelgaXploreApiService): LocationDetailsRepository {
        return LocationDetailsRepositoryImpl(api)
    }
}