package com.ashita.belgaxplore.domain.repository

import com.ashita.belgaxplore.domain.data.Locations

interface LocationsListRepository {

    suspend fun getLocationsList(): List<Locations>?
}