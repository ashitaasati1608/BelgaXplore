package com.ashita.belgaxplore.domain.usecase

import com.ashita.belgaxplore.domain.data.Locations
import com.ashita.belgaxplore.domain.repository.LocationsListRepository
import javax.inject.Inject

class LocationsListUseCase @Inject constructor(private val repository: LocationsListRepository) {

    suspend operator fun invoke(): List<Locations>? = repository.getLocationsList()
}

/* operator fun invoke(): Flow<Resources<List<Locations>>> = flow {
     println("USecase function got called")
     try {
         emit(Resources.Loading<List<Locations>>())
         val locationsList = repository.getLocationsList().map { it }
         emit(Resources.Success<List<Locations>>(locationsList))
     } catch (e: HttpException) {
         emit(Resources.Error<List<Locations>>(e.localizedMessage ?: "Resources not found"))
     } catch (e: IOException) {
         emit(Resources.Error<List<Locations>>("Couldn't reach server. Please check your internet connection"))
     }
 }*/
