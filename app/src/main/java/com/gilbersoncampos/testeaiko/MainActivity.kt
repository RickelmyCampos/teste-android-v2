package com.gilbersoncampos.testeaiko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.gilbersoncampos.testeaiko.commons.requestPermission
import com.gilbersoncampos.testeaiko.destinations.Destination
import com.gilbersoncampos.testeaiko.destinations.NavGraphHost
import com.gilbersoncampos.testeaiko.destinations.bottomAppBarList
import com.gilbersoncampos.testeaiko.destinations.getIcon
import com.gilbersoncampos.testeaiko.extensions.hasLocationPermission
import com.gilbersoncampos.testeaiko.features.busLines.navigateToBusLine
import com.gilbersoncampos.testeaiko.features.home.navigateToHome
import com.gilbersoncampos.testeaiko.ui.theme.TesteAikoTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val route =
                navController.currentBackStackEntryAsState().value?.destination?.route ?: ""
            val permissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            LaunchedEffect(key1 = !hasLocationPermission()) {
                permissionState.launchMultiplePermissionRequest()
            }
            requestPermission(permissionState)
            TesteAikoTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    NavigationBar {
                        bottomAppBarList.forEach {
                            NavigationBarItem(
                                selected = route == it.route,
                                onClick = {
                                    when (it) {
                                        Destination.BusLines -> {
                                            navController.navigateToBusLine(navOptions = navOptions {
                                                popUpTo(navController.graph.findStartDestination().id)
                                                launchSingleTop = true
                                            })
                                        }

                                        Destination.Home -> {
                                            navController.navigateToHome(navOptions = navOptions {
                                                popUpTo(navController.graph.findStartDestination().id)
                                                launchSingleTop = true
                                            })
                                        }
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = it.getIcon(),
                                        contentDescription = it.route
                                    )
                                },
                                label = { Text(text = it.name) })
                        }
                    }
                }) { innerPadding ->

                    App(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }

}

@Composable
fun App(modifier: Modifier = Modifier, navController: NavHostController) {
    NavGraphHost(modifier = modifier, navController = navController)

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TesteAikoTheme {
        //App()
    }
}