package com.ashita.belgaxplore.domain.repository

import com.ashita.belgaxplore.data.LocationsDto

interface LocationDetailsRepository {
    suspend fun getLocationDetails(id: String): LocationsDto

}