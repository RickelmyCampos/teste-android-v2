package com.gilbersoncampos.testeaiko.features.busLines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilbersoncampos.testeaiko.fakedata.busLineFakeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusLinesViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<BusLineUiState>(BusLineUiState.Loading)
    val uiState: StateFlow<BusLineUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000L)
            _uiState.value = BusLineUiState.Success(
                busLineFakeList
            )
        }
    }
}

sealed interface BusLineUiState {
    data object Loading : BusLineUiState
    data class Success(val busLineList: List<BusLine>) : BusLineUiState
    data class Error(val message: String) : BusLineUiState
}

data class BusLine(val id: String, val name: String, val codeBus: String, val direction: String)