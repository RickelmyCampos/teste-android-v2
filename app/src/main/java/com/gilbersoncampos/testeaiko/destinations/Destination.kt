package com.gilbersoncampos.testeaiko.destinations

sealed class Destination(val route: String, val name: String) {
    data object Home : Destination("home", "Home")
    data object BusLines:Destination("bus_lines","Linhas")
}
