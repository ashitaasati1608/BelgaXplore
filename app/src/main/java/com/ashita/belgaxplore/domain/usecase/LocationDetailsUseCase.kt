package com.ashita.belgaxplore.domain.usecase

import com.ashita.belgaxplore.domain.data.Locations
import com.ashita.belgaxplore.domain.repository.LocationDetailsRepository
import javax.inject.Inject

class LocationDetailsUseCase @Inject constructor(private val repository: LocationDetailsRepository) {

    suspend operator fun invoke(id: String): Locations? = repository.getLocationDetails(id)/*flow {
        try {
            emit(Resources.Loading<Locations>())

            val locationDetails = repository.getLocationDetails(id)
            emit(Resources.Success<Locations>(locationDetails!!))
        } catch (e: HttpException) {
            emit(Resources.Error<Locations>(e.localizedMessage ?: "Resources not found"))
        } catch (e: IOException) {
            emit(Resources.Error<Locations>("Couldn't reach server. Please check your internet connection"))
        }*/
    }
