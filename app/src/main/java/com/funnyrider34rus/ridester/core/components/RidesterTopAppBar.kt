package com.funnyrider34rus.ridester.core.components

import androidx.annotation.StringRes
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
    @StringRes titleRes: Int,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable (RowScope.() -> Unit)
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(titleRes),
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
        titleRes = android.R.string.untitled,
        modifier = Modifier,
        navigationIcon = { },
        actions = { }
    )
}