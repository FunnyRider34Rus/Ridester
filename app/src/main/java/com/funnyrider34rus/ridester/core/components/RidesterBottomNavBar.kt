package com.funnyrider34rus.ridester.core.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.navigation.Screen

@Composable
fun RidesterBottomNavBar(navController: NavController) {
    val bottomNavBarScreens = listOf(
        RidesterBottomNavBarItem.Dashboard,
        RidesterBottomNavBarItem.Chat,
        RidesterBottomNavBarItem.Ride,
        RidesterBottomNavBarItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = bottomNavBarScreens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            modifier = Modifier.height(64.dp),
            backgroundColor = Color.White,
        ) {
            bottomNavBarScreens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: RidesterBottomNavBarItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        label = {
            Text(
                text = stringResource(screen.lable),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )
        },
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = stringResource(screen.lable),
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        selected = selected,
        alwaysShowLabel = false,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

sealed class RidesterBottomNavBarItem(
    val route: String,
    @StringRes val lable: Int,
    @DrawableRes val icon: Int
) {
    object Dashboard : RidesterBottomNavBarItem(
        route = Screen.DASHBOARDLIST.route,
        lable = R.string.bottomnavbar_lable_dashboard,
        icon = R.drawable.ic_dashboard_outline
    )

    object Chat : RidesterBottomNavBarItem(
        route = Screen.CHAT.route,
        lable = R.string.bottomnavbar_lable_chat,
        icon = R.drawable.ic_messages_outline
    )

    object Ride : RidesterBottomNavBarItem(
        route = Screen.RIDE.route,
        lable = R.string.bottomnavbar_lable_ride,
        icon = R.drawable.ic_location_map_outline
    )

    object Profile : RidesterBottomNavBarItem(
        route = Screen.PROFILE.route,
        lable = R.string.bottomnavbar_lable_profile,
        icon = R.drawable.ic_user_outline
    )
}