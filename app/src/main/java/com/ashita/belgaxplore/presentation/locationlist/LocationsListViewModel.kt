package com.ashita.belgaxplore.presentation.locationlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashita.belgaxplore.common.Resources
import com.ashita.belgaxplore.domain.usecase.LocationsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LocationsListViewModel @Inject constructor
    (private val locationsListUseCase: LocationsListUseCase) : ViewModel() {

    private val _state = mutableStateOf(LocationListState())
    val locationListState: State<LocationListState> = _state

    init {
        getLocationsList()
    }

    private fun getLocationsList() {
        locationsListUseCase().onEach { result ->
            when (result) {
                is Resources.Error -> {
                    _state.value =
                        LocationListState(isError = result.message ?: "Unexpected error occured")
                }
                is Resources.Success -> {
                    _state.value =
                        LocationListState(locationsList = result.data ?: emptyList())
                }
                is Resources.Loading -> {
                    _state.value = LocationListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}