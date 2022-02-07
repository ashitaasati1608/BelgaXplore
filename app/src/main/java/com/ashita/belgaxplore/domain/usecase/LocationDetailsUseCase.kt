package com.ashita.belgaxplore.domain.usecase

import com.ashita.belgaxplore.common.Resources
import com.ashita.belgaxplore.data.toLocationDetails
import com.ashita.belgaxplore.domain.data.LocationDetails
import com.ashita.belgaxplore.domain.repository.LocationDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LocationDetailsUseCase @Inject constructor(private val repository: LocationDetailsRepository) {

    operator fun invoke(id: String): Flow<Resources<LocationDetails>> = flow {
        try {
            emit(Resources.Loading<LocationDetails>())

            val locationDetails = repository.getLocationDetails(id).toLocationDetails()
            emit(Resources.Success<LocationDetails>(locationDetails))
        } catch (e: HttpException) {
            emit(Resources.Error<LocationDetails>(e.localizedMessage ?: "Resources not found"))
        } catch (e: IOException) {
            emit(Resources.Error<LocationDetails>("Couldn't reach server. Please check your internet connection"))
        }
    }

}