package com.ashita.belgaxplore.domain.usecase

import com.ashita.belgaxplore.common.Resources
import com.ashita.belgaxplore.data.toLocations
import com.ashita.belgaxplore.domain.data.Locations
import com.ashita.belgaxplore.domain.repository.LocationsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LocationsListUseCase @Inject constructor(private val repository: LocationsListRepository) {

    operator fun invoke(): Flow<Resources<List<Locations>>> = flow {
        try {
            emit(Resources.Loading<List<Locations>>())
            val locationsList = repository.getLocationsList().map { it.toLocations() }
            emit(Resources.Success<List<Locations>>(locationsList))
        } catch (e: HttpException) {

            emit(Resources.Error<List<Locations>>(e.localizedMessage ?: "Resources not found"))
        } catch (e: IOException) {
            emit(Resources.Error<List<Locations>>("Couldn't reach server. Please check your internet connection"))
        }
    }


}