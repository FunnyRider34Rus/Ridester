package com.funnyrider34rus.ridester.core.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RidesterCenterTopAppBar(
    modifier: Modifier,
    @StringRes title: Int,
    @DrawableRes navigationIcon: Int?,
    @DrawableRes actionIcon: Int?,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title =
        {
            Text(
                text = stringResource(id = title),
                color = MaterialTheme.colorScheme.onSecondary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                navigationIcon?.let { painterResource(id = it) }?.let {
                    Icon(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                actionIcon?.let { painterResource(it) }?.let {
                    Icon(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RidesterCenterTopAppBar(
    modifier: Modifier,
    title: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSecondary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RidesterCenterTopAppBar(
    modifier: Modifier,
    profileImage: String,
    profileName: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = profileName,
                color = MaterialTheme.colorScheme.onSecondary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier,
        navigationIcon = {
            CoilImage(
                imageModel = { profileImage },
                modifier = Modifier.padding(start = 16.dp).size(32.dp).clip(CircleShape)
            )
        }
    )
}