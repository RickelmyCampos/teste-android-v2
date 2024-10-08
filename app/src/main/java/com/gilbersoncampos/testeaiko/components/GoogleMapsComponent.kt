package com.gilbersoncampos.testeaiko.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun GoogleMapComponent() {
    var isMapLoaded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        GoogleMap(modifier = Modifier.fillMaxSize(), onMapLoaded = { isMapLoaded = true })
    }
}