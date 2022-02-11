package com.ashita.belgaxplore.domain.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.ashita.belgaxplore.data.BelgaXploreApiService
import com.ashita.belgaxplore.domain.data.Locations
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class LocationDetailsRepositoryImpl @Inject constructor(
    private val apiService: BelgaXploreApiService
) : LocationDetailsRepository {

    override suspend fun getLocationDetails(id: String) = apiService.getLocationDetailsById(id)

}