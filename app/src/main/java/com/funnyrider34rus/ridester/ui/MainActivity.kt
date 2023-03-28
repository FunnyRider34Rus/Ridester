package com.funnyrider34rus.ridester.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.funnyrider34rus.ridester.core.navigation.Navigation
import com.funnyrider34rus.ridester.core.navigation.Screen
import com.funnyrider34rus.ridester.ui.theme.RidesterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RidesterTheme {
                RidesterApp()
            }
        }
    }
}

@Composable
fun RidesterApp() {
    Navigation(
        navController = rememberNavController(),
        startDestination = Screen.LOGIN.route
    )
}