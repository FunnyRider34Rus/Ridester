package com.funnyrider34rus.ridester.ui.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterCenterTopAppBar
import com.funnyrider34rus.ridester.core.util.timestampToDate
import com.funnyrider34rus.ridester.domain.model.DashboardContent
import com.funnyrider34rus.ridester.domain.model.DashboardType
import com.funnyrider34rus.ridester.ui.dashboard.DashboardEvent
import com.funnyrider34rus.ridester.ui.dashboard.DashboardVewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin

@Composable
fun DashboardItem(
    modifier: Modifier,
    content: DashboardContent
) {
    Column(
        modifier = modifier
    ) {
        RidesterCenterTopAppBar(
            title = content.title.toString(),
            modifier = Modifier
        )
        ContentBody(
            modifier = Modifier,
            content = content
            )

        Footer(
            modifier = Modifier.wrapContentHeight(Alignment.CenterVertically),
            content = content
        )
    }
}

@Composable
fun ContentBody(modifier: Modifier, content: DashboardContent) {
    val painter = rememberAsyncImagePainter(content.image)
    Box(modifier = modifier) {
        CoilImage(
            imageModel = { content.image },
            modifier = Modifier.fillMaxSize(),
            component = rememberImageComponent {
                +BlurTransformationPlugin(radius = 32)
            },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight
            )
        )
        Image(
            painter = painter,
            contentDescription = content.title,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
        )
        Text(
            text = content.body.toString(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 32.dp),
            color = MaterialTheme.colorScheme.background,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = when (content.type) {
                DashboardType.NEWS -> "#новость"
                DashboardType.POST -> "#пост"
                else -> ""
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(ButtonDefaults.TextButtonContentPadding),
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = timestampToDate(content.timestamp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(ButtonDefaults.TextButtonContentPadding),
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun Footer(
    modifier: Modifier,
    content: DashboardContent,
    viewModel: DashboardVewModel = hiltViewModel()
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                viewModel.onEvent(DashboardEvent.LikeClick)
            },
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_like_outline),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = if (content.likes.isNullOrEmpty()) "0" else content.likes.size.toString()
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.ic_comment_outline),
                contentDescription = null
            )
        }
        Text(
            text = "0"
        )
        Spacer(modifier = modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}