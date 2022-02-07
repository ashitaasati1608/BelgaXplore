package com.ashita.belgaxplore.domain.data

data class LocationDetails(
    val id: String,
    val name: String,
    val bestTimeToVisit: String? = null,
    val poster: String? = null,
    val description: String? = null,
    val link: String? = "null"
)