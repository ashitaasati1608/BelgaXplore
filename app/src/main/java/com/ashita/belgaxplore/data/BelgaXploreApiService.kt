package com.ashita.belgaxplore.data

import retrofit2.http.GET
import retrofit2.http.Path

interface BelgaXploreApiService {

    @GET("v1/location/{id}")
    suspend fun getLocationDetailsById(@Path("id") id: String): LocationsDto

    @GET("v1/location")
    suspend fun getCountryLocationsList(): List<LocationsDto>

    @GET("")
    suspend fun getCountriesList(): List<Country>
}