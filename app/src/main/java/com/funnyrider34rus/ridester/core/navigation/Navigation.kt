package com.funnyrider34rus.ridester.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.funnyrider34rus.ridester.core.components.RidesterBottomNavBar
import com.funnyrider34rus.ridester.ui.chat.ScreenChat
import com.funnyrider34rus.ridester.ui.dashboard.ScreenDashboard
import com.funnyrider34rus.ridester.ui.login.ScreenLogIn
import com.funnyrider34rus.ridester.ui.profile.ScreenProfile
import com.funnyrider34rus.ridester.ui.ride.ScreenRide

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController, startDestination: String) {
    Scaffold(
        bottomBar = { RidesterBottomNavBar(navController = navController) }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        NavHost(
            navController = navController,
            startDestination = startDestination,
            route = Screen.NAVGRAPH.route
        ) {
            composable(route = Screen.LOGIN.route) {
                ScreenLogIn(navController = navController)
            }
            composable(route = Screen.DASHBOARDLIST.route) {
                ScreenDashboard(navController = navController, modifier = Modifier)
            }
            composable(route = Screen.CHAT.route) {
                ScreenChat()
            }
            composable(route = Screen.RIDE.route) {
                ScreenRide()
            }
            composable(route = Screen.PROFILE.route) {
                ScreenProfile()
            }
        }
    }
}

sealed class Screen(val route: String) {
    object NAVGRAPH : Screen(route = "root_nav_graph")
    object LOGIN : Screen(route = "login_screen")
    object DASHBOARDLIST : Screen(route = "dashboard_list")
    object DASHBOARDDETAIL : Screen(route = "dashboard_detail")
    object CHAT : Screen(route = "chat")
    object RIDE : Screen(route = "ride")
    object PROFILE : Screen(route = "profile")
}

