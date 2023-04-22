package com.funnyrider34rus.ridester.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterErrorWidget
import com.funnyrider34rus.ridester.core.navigation.Navigation
import com.funnyrider34rus.ridester.core.navigation.Screen
import com.funnyrider34rus.ridester.core.theme.RidesterTheme
import com.funnyrider34rus.ridester.core.util.InternetConnectionState
import com.funnyrider34rus.ridester.core.util.connectivityState
import com.funnyrider34rus.ridester.domain.model.UserOnlineStatus
import com.funnyrider34rus.ridester.domain.repository.AuthRepository
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.gms.common.GoogleApiAvailability
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RidesterTheme {
                RidesterApp()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            authRepository.writeUserToDB(UserOnlineStatus.ONLINE)
        }
    }

    override fun onPause() {
        super.onPause()
        lifecycleScope.launch {
            authRepository.writeUserToDB(UserOnlineStatus.OFFLINE)
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

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun CheckUserAuth() {
        val startDestination = if (viewModel.isUserAuthenticated) Screen.DASHBOARDLIST.route else Screen.LOGIN.route
        Navigation(
            navController = rememberAnimatedNavController(),
            startDestination = startDestination
        )
    }

}





