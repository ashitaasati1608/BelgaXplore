package com.ashita.belgaxplore.presentation.locationdetails

import com.ashita.belgaxplore.domain.data.LocationDetails

data class LocationDetailsState(
    val isError: String = "",
    val isLoading: Boolean = false,
    val locationDetails: LocationDetails?= null
)
