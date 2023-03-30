package com.funnyrider34rus.ridester.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.funnyrider34rus.ridester.core.navigation.Navigation
import com.funnyrider34rus.ridester.core.navigation.Screen
import com.funnyrider34rus.ridester.core.theme.RidesterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RidesterTheme {
                RidesterApp()
            }
        }
    }

    @Composable
    fun RidesterApp() {
        val startDestination = if (viewModel.isUserAuthenticated) Screen.DASHBOARDLIST.route else Screen.LOGIN.route

        Navigation(
            navController = rememberNavController(),
            startDestination = startDestination
        )
    }
}

