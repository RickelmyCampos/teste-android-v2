package com.gilbersoncampos.testeaiko.features.busLines

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gilbersoncampos.testeaiko.destinations.Destination

val ROUTE = Destination.BusLines.route
fun NavGraphBuilder.busLineScreen() {
    composable(ROUTE) {
        BusLinesScreen()
    }
}
fun NavHostController.navigateToBusLine(navOptions: NavOptions? = null) {
    navigate(
        route = ROUTE,
        navOptions = navOptions
    )
}