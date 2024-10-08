package com.gilbersoncampos.testeaiko.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilbersoncampos.testeaiko.data.remote.datasource.ApiDataSource
import com.gilbersoncampos.testeaiko.useCase.GetLocationUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getLocationUseCase: GetLocationUseCase, private val service:ApiDataSource) :
    ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    fun handleLocation() {
        viewModelScope.launch {
            getLocationUseCase.invoke().collect { location ->
               // Log.d("Location", "$location")
                _uiState.value = HomeUiState.Success(location = location)
            }

        }
    }
    fun fetchApi(){
        viewModelScope.launch {
            service.searchBusStopByTerm()
        }
    }

}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val location: LatLng?) : HomeUiState
}