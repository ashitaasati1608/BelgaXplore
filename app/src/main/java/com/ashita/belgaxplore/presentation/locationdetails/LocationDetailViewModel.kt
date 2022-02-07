package com.ashita.belgaxplore.presentation.locationdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ashita.belgaxplore.common.Constants.PARAM_LOCATION_ID
import com.ashita.belgaxplore.common.Resources
import com.ashita.belgaxplore.domain.usecase.LocationDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    val locationDetailsUseCase: LocationDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(LocationDetailsState())
    val locationDetailsState: State<LocationDetailsState> = _state


    init {
        savedStateHandle.get<String>(PARAM_LOCATION_ID)?.let { locationId ->
            fetchDetails(locationId)
        }
    }

    private fun fetchDetails(id: String) {
        locationDetailsUseCase(id).onEach { result ->
            when (result) {

                is Resources.Error -> {
                    _state.value =
                        LocationDetailsState(errorMessage = result.message ?: "Unexpected error occured")
                }
                is Resources.Success -> {
                    _state.value = LocationDetailsState(locationDetails = result.data)
                }
                is Resources.Loading -> {
                    _state.value = LocationDetailsState(isLoading = true)
                }
            }

        }
    }
}