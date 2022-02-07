package com.ashita.belgaxplore.domain.repository

import com.ashita.belgaxplore.data.BelgaXploreApiService
import javax.inject.Inject

class LocationsListRepositoryImpl @Inject constructor(private val apiService: BelgaXploreApiService) :
    LocationsListRepository {

    override suspend fun getLocationsList() = apiService.getCountryLocationsList()

}