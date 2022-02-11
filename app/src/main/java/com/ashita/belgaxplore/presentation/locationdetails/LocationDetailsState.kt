package com.ashita.belgaxplore.presentation.locationdetails

import com.ashita.belgaxplore.domain.data.Locations

data class LocationDetailsState(
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val locationDetails: Locations?= null
)
