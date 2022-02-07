package com.ashita.belgaxplore.domain.repository

import com.ashita.belgaxplore.data.LocationsDto

interface LocationsListRepository {

    suspend fun getLocationsList(): List<LocationsDto>
}