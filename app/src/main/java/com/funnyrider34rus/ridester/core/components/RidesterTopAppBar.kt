package com.funnyrider34rus.ridester.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RidesterTopAppBar(
    modifier: Modifier,
    title: String,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable (RowScope.() -> Unit)
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    RidesterTopAppBar(
        title = stringResource(id = android.R.string.untitled),
        modifier = Modifier,
        navigationIcon = { },
        actions = { }
    )
}