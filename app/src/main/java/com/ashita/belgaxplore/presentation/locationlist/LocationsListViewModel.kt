package com.ashita.belgaxplore.presentation.locationlist

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashita.belgaxplore.common.Resources
import com.ashita.belgaxplore.domain.usecase.LocationsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsListViewModel @Inject constructor
    (var locationsListUseCase: LocationsListUseCase) : ViewModel() {

    private val _state = mutableStateOf(LocationListState())
    val locationListState: State<LocationListState> = _state

    init {
        getLocationsList()
    }

    fun getLocationsList() {
        println("View Model function got called")
        viewModelScope.launch {
           _state.value = LocationListState(locationsList = locationsListUseCase())
            /*locationsListUseCase().onEach { result ->
                when (result) {
                    is Resources.Error -> {
                        _state.value =
                            LocationListState(isError = result.message ?: "Unexpected error occured")
                    }
                    is Resources.Success -> {

                    }
                    is Resources.Loading -> {
                        _state.value = LocationListState(isLoading = true)
                    }
                }
            }*/
        }

    }

    /*val locations =
        listOf(
            LocationDetails(
                "Brussels",
                "May-August",
                "",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available."
            ),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations(
                "Brussels",
                "May-August",
                "",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available."
            ),
            Locations("Ghent", "June-Mid Oct", "", "Usually rainy"),
            Locations("Signal de Botrange", "Dec - Feb", "", "Usually rainy"),
            Locations("Durbuy", "May-August", "", "Usually rainy"),
            Locations("Dinant", "May-August", "", "Usually rainy"),
            Locations("Liege", "May-August", "", "Usually rainy"),
            Locations("Libramont", "May-August", "", "Usually rainy"),
            Locations("Turvuren", "May-August", "", "Usually rainy"),
            Locations("Brussels", "May-August", "", "Usually rainy"),
            Locations("Brussels", "May-August", "", "Usually rainy"),
            Locations("Brussels", "May-August", "", "Usually rainy"),
            Locations("Brussels", "May-August", "", "Usually rainy"),
            Locations("Brussels", "May-August", "", "Usually rainy"),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations("Antwerp", "May-August", "", "Usually rainy"),
            Locations("Ghent", "May-August", "", "Usually rainy"),
            Locations("Ghent", "May-August", "", "Usually rainy"),
            Locations("Ghent", "May-August", "", "Usually rainy"),
            Locations("Ghent", "May-August", "", "Usually rainy")

        )*/

}