package com.funnyrider34rus.ridester.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterLoadingWidget
import com.funnyrider34rus.ridester.core.components.RidesterTopAppBar
import com.funnyrider34rus.ridester.ui.dashboard.components.DashboardItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenDashboard(
    modifier: Modifier,
    navigateToComment: () -> Unit,
    navigateToAddPost: () -> Unit,
    state: DashboardViewState,
    onEvent: (DashboardEvent) -> Unit
) {

    val lazyListState = rememberLazyListState()

    Box(modifier = modifier) {
        Column(modifier = Modifier) {
            RidesterTopAppBar(
                title = R.string.bottomnavbar_lable_dashboard,
                modifier = Modifier,
                navigationIcon = { },
                actions = {
                    IconButton(
                        onClick = navigateToAddPost
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_write_outline),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
            ) {
                items(state.content) { item ->
                    DashboardItem(
                        modifier = Modifier.fillParentMaxHeight(),
                        content = item,
                        navigateToComment = navigateToComment,
                        state = state,
                        onEvent = onEvent
                    )
                }
            }

        }

        if (state.isLoading) RidesterLoadingWidget(modifier = Modifier.fillMaxSize())
    }
}