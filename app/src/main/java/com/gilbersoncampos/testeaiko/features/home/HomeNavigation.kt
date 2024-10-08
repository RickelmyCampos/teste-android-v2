package com.gilbersoncampos.testeaiko.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavGraphNavigator
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gilbersoncampos.testeaiko.destinations.Destination

val ROUTE = Destination.Home.route
fun NavGraphBuilder.homeScreen() {
    composable(ROUTE) {
        HomeScreen()
    }
}
fun NavHostController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(
        route = ROUTE,
        navOptions = navOptions
    )
}