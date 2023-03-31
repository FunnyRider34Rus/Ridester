package com.funnyrider34rus.ridester.core.components

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RidesterCenterTopAppBar(
    @StringRes titleRes: Int,
    navigationIcon: ImageVector,
    actionIcon: ImageVector,
    modifier: Modifier,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title =
        {
            Text(
                text = stringResource(id = titleRes),
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = null
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RidesterCenterTopAppBar(
    @StringRes titleRes: Int,
    modifier: Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun CenterTopAppBarPreview() {
    RidesterCenterTopAppBar(
        titleRes = android.R.string.untitled,
        navigationIcon = Icons.Default.Search,
        actionIcon = Icons.Default.Menu,
        modifier = Modifier
    )
}