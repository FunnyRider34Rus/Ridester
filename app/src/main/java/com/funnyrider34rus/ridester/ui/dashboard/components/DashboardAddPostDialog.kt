package com.funnyrider34rus.ridester.ui.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.ui.dashboard.DashboardEvent
import com.funnyrider34rus.ridester.ui.dashboard.DashboardVewModel
import com.funnyrider34rus.ridester.ui.dashboard.DashboardViewState

@Composable
fun DashboardAddPostDialog(modifier: Modifier, viewModel: DashboardVewModel = hiltViewModel()) {

    val viewState by viewModel.viewState.collectAsState(DashboardViewState())

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .background(MaterialTheme.colorScheme.background)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_outline),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            OutlinedTextField(
                value = viewState.title,
                onValueChange = { viewModel.onEvent(DashboardEvent.EnteredTitle(it)) },
                modifier = Modifier.padding(top = 16.dp),
                label = { Text("Заголовок") }
            )
            OutlinedTextField(
                value = viewState.body,
                onValueChange = { viewModel.onEvent(DashboardEvent.EnteredBody(it)) },
                modifier = Modifier.padding(top = 16.dp),
                label = { Text("Сообщение") }
            )
        }
    }
}

@Preview
@Composable
fun AddPostPreview() {
    DashboardAddPostDialog(modifier = Modifier)
}