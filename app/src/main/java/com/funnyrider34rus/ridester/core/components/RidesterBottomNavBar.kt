package com.funnyrider34rus.ridester.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
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
        NavigationBar {
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
    NavigationBarItem(
        label = {
            Text(text = stringResource(screen.lable))
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = stringResource(screen.lable)
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
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
    val icon: ImageVector
) {
    object Dashboard : RidesterBottomNavBarItem(
        route = Screen.DASHBOARDLIST.route,
        lable = R.string.bottomnavbar_lable_dashboard,
        icon = Icons.Default.Dashboard
    )

    object Chat : RidesterBottomNavBarItem(
        route = Screen.CHAT.route,
        lable = R.string.bottomnavbar_lable_chat,
        icon = Icons.Default.Message
    )

    object Ride : RidesterBottomNavBarItem(
        route = Screen.RIDE.route,
        lable = R.string.bottomnavbar_lable_ride,
        icon = Icons.Default.SportsMotorsports
    )

    object Profile : RidesterBottomNavBarItem(
        route = Screen.PROFILE.route,
        lable = R.string.bottomnavbar_lable_profile,
        icon = Icons.Default.Person
    )
}