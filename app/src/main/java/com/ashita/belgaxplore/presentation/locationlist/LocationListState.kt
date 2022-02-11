package com.ashita.belgaxplore.presentation.locationlist

import com.ashita.belgaxplore.domain.data.Locations


data class LocationListState(
    val isError: String = "",
    val isLoading: Boolean = false,
    val locationsList: List<Locations>? = emptyList()
)
