package com.gilbersoncampos.testeaiko.features.busList

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gilbersoncampos.testeaiko.destinations.Destination
import com.gilbersoncampos.testeaiko.features.home.HomeScreen

val ROUTE = Destination.BusLines.route
fun NavGraphBuilder.busLineScreen() {
    composable(ROUTE) {
        HomeScreen()
    }
}
fun NavHostController.navigateToBusLine(navOptions: NavOptions? = null) {
    navigate(
        route = ROUTE,
        navOptions = navOptions
    )
}