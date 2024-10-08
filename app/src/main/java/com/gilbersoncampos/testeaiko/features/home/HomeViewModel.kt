package com.gilbersoncampos.testeaiko.features.home

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilbersoncampos.testeaiko.data.model.BusStopModel
import com.gilbersoncampos.testeaiko.data.remote.datasource.ApiDataSource
import com.gilbersoncampos.testeaiko.features.busLines.BusLineUiState
import com.gilbersoncampos.testeaiko.useCase.GetLocationUseCase
import com.gilbersoncampos.testeaiko.useCase.SearchBusStopByTermUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val searchBusStopByTermUseCase: SearchBusStopByTermUseCase
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    fun handleLocation() {
        viewModelScope.launch {
            var lastFetchTime = System.currentTimeMillis()
            getLocationUseCase.invoke().collect { location ->
                _uiState.value = HomeUiState.Success(location = location, listBusStop = listOf(
                    BusStopModel("12", name = "teste", position = LatLng(-23.59572,-46.673285)),
                    BusStopModel("12", name = "teste", position = LatLng(-23.594375,-46.673018))
                ))
                location?.let {
                    val distance = lastLocation?.let { calculateDistance(it, location) }
                        ?: (locationUpdateThreshold + 1)
                    val currentTime = System.currentTimeMillis()
                    if (distance > locationUpdateThreshold || currentTime - lastFetchTime > fetchIntervalMillis) {
                        fetchApi()
                        lastFetchTime = currentTime
                        lastLocation = location
                    }
                }
            }
        }
    }

    fun fetchApi() {
        viewModelScope.launch {
            val listBusStop = searchBusStopByTermUseCase.invoke("Afonso")
            _uiState.update { currentState ->
                when (currentState) {
                    is HomeUiState.Success -> currentState.copy(listBusStop = listBusStop)
                    else -> currentState
                }
            }
        }
    }

    private fun calculateDistance(start: LatLng, end: LatLng): Double {
        val result = FloatArray(1)
        Location.distanceBetween(
            start.latitude, start.longitude,
            end.latitude, end.longitude,
            result
        )
        return result[0].toDouble()
    }

    companion object {
        private var lastLocation: LatLng? = null
        private val locationUpdateThreshold = 1000.0
        private val fetchIntervalMillis = 5 * 60 * 1000L
    }

}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val location: LatLng?, val listBusStop: List<BusStopModel>) : HomeUiState
}