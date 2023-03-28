package com.funnyrider34rus.ridester.ui.login

import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterTopAppBar

@Composable
fun ScreenLogIn(
    viewModel: LogInViewModel = hiltViewModel(),
    navigateToNextScreen: () -> Unit
) {

    val containerScrollState = rememberScrollState()
    val textScrollState = rememberScrollState()
    val viewState = viewModel.viewState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RidesterTopAppBar(
                title = stringResource(id = R.string.screen_login_title),
                modifier = Modifier
            )
        }
    ) { paddingValues ->
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
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = viewState.value.isCheck,
                    onCheckedChange = { viewModel.onEvent(LogInEvent.CheckBoxClick) }
                )
                Text(
                    text = stringResource(id = R.string.screen_login_checkbox),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Button(
                onClick = { viewModel.onEvent(LogInEvent.ButtonClick) },
                modifier = Modifier.fillMaxWidth(),
                enabled = viewState.value.isCheck,
                contentPadding = ButtonDefaults.ContentPadding
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "SignIn with Google",
                    modifier = Modifier.size(12.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = stringResource(id = R.string.screen_login_button),
                    style = androidx.compose.material.MaterialTheme.typography.button
                )
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Preview
@Composable
fun LogiInPreview() {
    ScreenLogIn(navigateToNextScreen = { })
}