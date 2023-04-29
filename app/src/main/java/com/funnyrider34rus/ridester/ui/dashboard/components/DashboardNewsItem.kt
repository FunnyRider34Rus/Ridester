package com.funnyrider34rus.ridester.ui.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterCenterTopAppBar
import com.funnyrider34rus.ridester.core.util.getLikeStatus
import com.funnyrider34rus.ridester.core.util.timestampToDate
import com.funnyrider34rus.ridester.domain.model.DashboardContent
import com.funnyrider34rus.ridester.ui.dashboard.DashboardEvent
import com.funnyrider34rus.ridester.ui.dashboard.DashboardViewState
import com.funnyrider34rus.ridester.ui.dashboard.LikesStatus
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin

@Composable
fun DashboardNewsItem(
    modifier: Modifier,
    content: DashboardContent,
    navigateToComment: () -> Unit,
    state: DashboardViewState,
    onEvent: (DashboardEvent) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        RidesterCenterTopAppBar(
            title = content.title.toString(),
            modifier = Modifier
        )

        ContentNewsBody(
            modifier = Modifier.weight(1f),
            content = content,
            state = state,
            onEvent = onEvent
        )

        ContentNewsFooter(
            modifier = Modifier.wrapContentHeight(Alignment.CenterVertically),
            content = content,
            navigateToComment = navigateToComment,
            onEvent = onEvent
        )
    }
}

@Composable
fun ContentNewsBody(modifier: Modifier, content: DashboardContent, state: DashboardViewState, onEvent: (DashboardEvent) -> Unit) {

    val painter = rememberAsyncImagePainter(content.image)
    var isEllipsis by remember { mutableStateOf(false) }
    val scroll = rememberScrollState(0)

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
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .clickable {
                    if (isEllipsis) onEvent(DashboardEvent.ContentClick)
                }
                .verticalScroll(scroll),
            color = MaterialTheme.colorScheme.background,
            overflow = if (state.isBodyExpand) TextOverflow.Visible else TextOverflow.Ellipsis,
            maxLines = if (state.isBodyExpand) Int.MAX_VALUE else 3,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.hasVisualOverflow) isEllipsis = true
            },
            style = if (state.isBodyExpand) MaterialTheme.typography.headlineSmall else MaterialTheme.typography.bodyLarge
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
fun ContentNewsFooter(
    modifier: Modifier,
    content: DashboardContent,
    navigateToComment: () -> Unit,
    onEvent: (DashboardEvent) -> Unit
) {
    Row(
        modifier = modifier
            .height(64.dp)
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onEvent(DashboardEvent.LikeClick(content)) }
        ) {
            Icon(
                painter = when (getLikeStatus(content.likes)) {
                    LikesStatus.NONE -> painterResource(R.drawable.ic_like_outline)
                    else -> painterResource(R.drawable.ic_like)
                },
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = when (getLikeStatus(content.likes)) {
                    LikesStatus.LIKE -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.primary
                }
            )
        }
        Text(
            text = if (content.likes.isNullOrEmpty()) "0" else content.likes.size.toString(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
        )
        IconButton(
            onClick = navigateToComment,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_comment_outline),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = "0",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
        )
        Spacer(modifier = modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun DashboardNewsPreview() {
    DashboardNewsItem(
        modifier = Modifier,
        content = DashboardContent(),
        navigateToComment = {  },
        state = DashboardViewState(),
        onEvent = {  }
    )
}