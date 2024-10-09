package com.gilbersoncampos.testeaiko.destinations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gilbersoncampos.testeaiko.features.busLines.busLineScreen
import com.gilbersoncampos.testeaiko.features.home.homeScreen

@Composable
fun NavGraphHost(navController: NavHostController,modifier: Modifier) {
    NavHost(navController = navController, startDestination = Destination.Home.route, modifier = modifier) {
        homeScreen()
        busLineScreen()
    }
}