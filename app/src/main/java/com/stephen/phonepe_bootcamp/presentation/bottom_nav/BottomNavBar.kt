package com.stephen.phonepe_bootcamp.presentation.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stephen.phonepe_bootcamp.presentation.bottom_nav.components.BottomNavItem
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LighterGray
import com.stephen.phonepe_bootcamp.presentation.ui.theme.NavGreen

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp)
            .background(LighterGray)
            .padding(top = 10.dp, bottom = 10.dp)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            BottomNavItem.bottomNavItems.forEach { item ->
                AddItem(item, currentRoute, navController)

            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    bottomItem: BottomNavItem,
    currentRoute: String?,
    navController: NavController
) {
    val isSelected = currentRoute == bottomItem.route
    val bgColor = if (isSelected) NavGreen else Color(0xFFE8E8E8)
    val iconColor = if (isSelected) Color.White else Color.Black
    val scale = if (isSelected) 1.2f else 1.0f

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(bgColor)
            .padding(8.dp)
            .scale(scale)
            .noRippleClickable (
                onClick = {
                    if (currentRoute != bottomItem.route) {
                        navController.navigate(bottomItem.route) {
                            // this will pop everything above the start destination and take me to the start destination
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
    ) {
        Icon(
            painter = painterResource(bottomItem.icon),
            contentDescription = bottomItem.label,
            tint = iconColor,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )
    }
}


// Kotlin extension function - https://proandroiddev.com/remove-ripple-effect-from-clickable-and-toggleable-widget-in-jetpack-compose-16b154265283
inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}