package com.ashita.belgaxplore.di

import com.ashita.belgaxplore.common.Constants.BASE_URL
import com.ashita.belgaxplore.data.BelgaXploreApiService
import com.ashita.belgaxplore.domain.repository.LocationDetailsRepository
import com.ashita.belgaxplore.domain.repository.LocationDetailsRepositoryImpl
import com.ashita.belgaxplore.domain.repository.LocationsListRepository
import com.ashita.belgaxplore.domain.repository.LocationsListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBelgaExploreApi(): BelgaXploreApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BelgaXploreApiService::class.java)
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