package com.funnyrider34rus.ridester.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.funnyrider34rus.ridester.R

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
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier.padding(8.dp),
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
        navigationIcon = { IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_write_outline),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        } },
        actions = { IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_write_outline),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        } }
    )
}