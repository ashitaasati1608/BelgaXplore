package com.ashita.belgaxplore.domain.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ashita.belgaxplore.data.BelgaXploreApiService
import com.ashita.belgaxplore.domain.data.Locations
import javax.inject.Inject

class LocationsListRepositoryImpl @Inject constructor(private val apiService: BelgaXploreApiService) :
    LocationsListRepository {

    override suspend fun getLocationsList() =
        apiService.getCountryLocationsList(SnapshotStateList<Locations>())

}