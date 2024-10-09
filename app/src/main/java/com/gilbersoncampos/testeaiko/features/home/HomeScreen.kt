package com.gilbersoncampos.testeaiko.features.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.gilbersoncampos.testeaiko.commons.requestPermission
import com.gilbersoncampos.testeaiko.components.GoogleMapComponent
import com.gilbersoncampos.testeaiko.data.model.BusStopModel
import com.gilbersoncampos.testeaiko.extensions.hasLocationPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.LatLng

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    val context = LocalContext.current
    LaunchedEffect(key1 = !context.hasLocationPermission()) {
        permissionState.launchMultiplePermissionRequest()
    }
    requestPermission(permissionState) {
        viewModel.handleLocation()
    }
    HomeUi(uiState.value)
    Button(onClick = { viewModel.fetchApi() }) {
        Text(text = "Request")
    }
}


@Composable
fun HomeUi(uiState: HomeUiState) {
    val (location, listBusStop) = when (uiState) {
        HomeUiState.Loading -> null to emptyList()
        is HomeUiState.Success -> uiState.location to uiState.listBusStop
    }
    GoogleMapComponent(location,listBusStop)

}