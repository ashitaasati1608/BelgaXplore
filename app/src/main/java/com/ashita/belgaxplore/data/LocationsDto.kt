package com.ashita.belgaxplore.data

import com.ashita.belgaxplore.domain.data.LocationDetails
import com.ashita.belgaxplore.domain.data.Locations

data class LocationsDto(
    val id: String,
    val name: String,
    val bestTimeToVisit: String? = null,
    val poster: String? = null,
    val description: String? = null,
    val link: String? = "null"
)

fun LocationsDto.toLocations(): Locations {
    return Locations(
        id = id,
        name = name,
        poster = poster
    )
}

fun LocationsDto.toLocationDetails(): LocationDetails {
    return LocationDetails(
        id = id,
        name = name,
        poster = poster,
        bestTimeToVisit = bestTimeToVisit,
        description = description,
        link = link
    )
}