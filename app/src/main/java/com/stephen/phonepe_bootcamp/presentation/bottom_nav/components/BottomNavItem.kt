package com.stephen.phonepe_bootcamp.presentation.bottom_nav.components

import com.stephen.phonepe_bootcamp.R


sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    object Home: BottomNavItem("home", R.drawable.round_home_24, "Home" )
    object Explore: BottomNavItem("explore", R.drawable.round_explore_24, "Explore" )
    object AddItem: BottomNavItem("add_item", R.drawable.round_add_shopping_cart_24, "Add" )

    companion object {
        val bottomNavItems = listOf(Home, Explore, AddItem)
    }
}