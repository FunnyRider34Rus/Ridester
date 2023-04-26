package com.funnyrider34rus.ridester.ui.dashboard.add_post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterCenterTopAppBar
import com.funnyrider34rus.ridester.core.components.RidesterOutlinedTextField

@Composable
fun ScreenDashboardAddPostDialog(
    navigateToBack: () -> Unit,
    viewModel: DashboardAddPostDialogViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsState(DashboardAddPostDialogViewState())
    val focusManager = LocalFocusManager.current

    Column {
        RidesterCenterTopAppBar(
            modifier = Modifier,
            title = R.string.screen_dashboard_add_post_appbar_title,
            navigationIcon = R.drawable.ic_button_back,
            actionIcon = R.drawable.ic_done,
            onNavigationClick = navigateToBack
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_media),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 88.dp)
                    .size(88.dp)
                    .clickable { /*TODO*/ },
                tint = MaterialTheme.colorScheme.primary
            )
            RidesterOutlinedTextField(
                value = viewState.body,
                onValueChange = { viewModel.onEvent(DashboardAddPostDialogEvent.EnteredBody(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 88.dp),
                label = R.string.screen_dashboard_add_post_textfield_body,
                focusManager = focusManager,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default,
                singleLine = false
            )
        }
    }
}