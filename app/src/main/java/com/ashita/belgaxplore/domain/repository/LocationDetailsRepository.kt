package com.ashita.belgaxplore.domain.repository

import com.ashita.belgaxplore.domain.data.Locations

interface LocationDetailsRepository {
    suspend fun getLocationDetails(id: String): Locations?

}