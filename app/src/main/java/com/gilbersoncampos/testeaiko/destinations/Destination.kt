package com.gilbersoncampos.testeaiko.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(val route: String, val name: String) {
    data object Home : Destination("home", "Home")
    data object BusLines : Destination("bus_lines", "Linhas")
}

val bottomAppBarList: List<Destination> = listOf(Destination.Home, Destination.BusLines)
fun Destination.getIcon(): ImageVector {
    return when (this) {
        Destination.Home -> Icons.Default.Home
        Destination.BusLines -> Icons.AutoMirrored.Filled.List
    }
}