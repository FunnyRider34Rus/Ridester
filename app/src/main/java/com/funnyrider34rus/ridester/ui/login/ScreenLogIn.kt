package com.funnyrider34rus.ridester.ui.login

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterCenterTopAppBar
import com.funnyrider34rus.ridester.core.components.RidesterErrorWidget
import com.funnyrider34rus.ridester.core.components.RidesterLoadingWidget
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenLogIn(
    navigateToMain: () -> Unit,
    state: LogInViewState,
    onEvent: (LogInEvent) -> Unit
) {

    val containerScrollState = rememberScrollState()
    val textScrollState = rememberScrollState()

    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            onEvent(LogInEvent.ButtonClick(credential))
        } catch (e: ApiException) {
            onEvent(LogInEvent.Error(e.localizedMessage))
        }
    }

    if (state.isUserAuth) {
        navigateToMain.invoke()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RidesterCenterTopAppBar(
                title = stringResource(id = R.string.screen_login_title),
                modifier = Modifier
            )
        }
    ) { paddingValues ->

        if (state.isLoading) {
            RidesterLoadingWidget(
                modifier = Modifier.padding(paddingValues)
            )
        }

        if (state.isError) {
            val activity = (LocalContext.current as? Activity)
            RidesterErrorWidget(
                modifier = Modifier.padding(paddingValues),
                title = "Error",
                text = state.error,
                buttonText = stringResource(id = R.string.screen_login_error_button),
                onButtonClick = { activity?.finish() }
            )
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(32.dp)
                .scrollable(state = containerScrollState, Orientation.Vertical)
        ) {
            Text(
                text = stringResource(id = R.string.screen_login_body),
                modifier = Modifier
                    .verticalScroll(textScrollState)
                    .clipScrollableContainer(Orientation.Vertical),
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.isCheck,
                    onCheckedChange = { onEvent(LogInEvent.CheckBoxClick) }
                )
                Text(
                    text = stringResource(id = R.string.screen_login_checkbox),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Button(
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()

                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSignInClient.signInIntent)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.isCheck,
                contentPadding = ButtonDefaults.ContentPadding
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = stringResource(id = R.string.screen_login_button_description),
                    modifier = Modifier.size(16.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = stringResource(id = R.string.screen_login_button),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
fun LoginInPreview() {
    ScreenLogIn(
        navigateToMain = {  },
        state = LogInViewState(),
        onEvent = {  }
    )
}