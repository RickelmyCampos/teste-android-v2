package com.gilbersoncampos.testeaiko.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gilbersoncampos.testeaiko.R
import com.gilbersoncampos.testeaiko.data.model.BusStopModel
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapComponent(position: LatLng?, listBusStop: List<BusStopModel> = emptyList()) {
    var isMapLoaded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        val cameraPositionState = rememberCameraPositionState()
        position?.let {
            LaunchedEffect(key1 = isMapLoaded) {
                if (isMapLoaded) {
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(position, 18f)
                }
            }
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            onMapLoaded = { isMapLoaded = true },
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = true,
                mapType = MapType.NORMAL,
                isTrafficEnabled = false
            )
        ) {

            listBusStop.forEach { busStop ->
                MarkerComposable(
                    state = MarkerState(position = busStop.position),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bus_stop),
                        contentDescription = "",
                        Modifier.size(20.dp)
                    )
                }
            }

        }

    }
}