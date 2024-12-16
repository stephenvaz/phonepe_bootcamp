package com.stephen.phonepe_bootcamp.presentation

sealed class Screens(val route: String) {
    object HomeScreen: Screens("home")
    object ExploreScreen: Screens("explore")
    object AddScreen: Screens("add_item")
}