package com.ashita.belgaxplore.domain.repository

import com.ashita.belgaxplore.data.BelgaXploreApiService
import javax.inject.Inject

class LocationDetailsRepositoryImpl @Inject constructor(
    private val apiService: BelgaXploreApiService
) : LocationDetailsRepository {

    override suspend fun getLocationDetails(id: String) = apiService.getLocationDetailsById(id)

}