package com.gilbersoncampos.testeaiko.commons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.gilbersoncampos.testeaiko.extensions.hasLocationPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestPermission(permissionState: MultiplePermissionsState, onPermissionGranted:()->Unit={}) {

    when {
        permissionState.allPermissionsGranted -> {
            onPermissionGranted()
        }

        permissionState.shouldShowRationale -> {
            //relaunch permissions
        }

        !permissionState.allPermissionsGranted && !permissionState.shouldShowRationale -> {
            LaunchedEffect(key1 = Unit) {
                // permissions revoked
            }
        }
    }
}