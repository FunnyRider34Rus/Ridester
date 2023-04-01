package com.funnyrider34rus.ridester.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterErrorWidget
import com.funnyrider34rus.ridester.core.navigation.Navigation
import com.funnyrider34rus.ridester.core.navigation.Screen
import com.funnyrider34rus.ridester.core.theme.RidesterTheme
import com.funnyrider34rus.ridester.core.util.InternetConnectionState
import com.funnyrider34rus.ridester.core.util.connectivityState
import com.google.android.gms.common.GoogleApiAvailability
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

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
        CheckGooglePlayServicesAvailable()
        CheckInternetConnection()
        CheckUserAuth()
    }

    @Composable
    fun CheckGooglePlayServicesAvailable() {
        val availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
            LocalContext.current) == com.google.android.gms.common.ConnectionResult.SUCCESS
        if (!availability) {
            RidesterErrorWidget(
                modifier = Modifier,
                title = stringResource(R.string.common_error_gms_title),
                text = stringResource(R.string.common_error_gms),
                buttonText = stringResource(R.string.common_error_gms_button),
                onButtonClick = { (this as ComponentActivity).finish() }
            )
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    private fun CheckInternetConnection() {
        val connection by connectivityState()
        val isConnected = connection === InternetConnectionState.Available
        if (!isConnected) {
            RidesterErrorWidget(
                modifier = Modifier,
                title = stringResource(R.string.common_error_connection_title),
                text = stringResource(R.string.common_error_connection),
                buttonText = stringResource(R.string.common_error_connection_button),
                onButtonClick = {  }
            )
        }
    }

    @Composable
    private fun CheckUserAuth() {
        val startDestination = if (viewModel.isUserAuthenticated) Screen.DASHBOARDLIST.route else Screen.LOGIN.route
        Navigation(
            navController = rememberNavController(),
            startDestination = startDestination
        )
    }

}





