package com.funnyrider34rus.ridester.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterLoadingWidget
import com.funnyrider34rus.ridester.core.components.RidesterTopAppBar
import com.funnyrider34rus.ridester.ui.dashboard.components.DashboardItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenDashboard(
    navController: NavController,
    modifier: Modifier,
    viewModel: DashboardVewModel = hiltViewModel()
    ) {

    val viewState by viewModel.viewState.collectAsState(DashboardViewState())
    val lazyListState = rememberLazyListState()

    if (viewState.isLoading) RidesterLoadingWidget(modifier = Modifier.fillMaxSize())

    Column(modifier = modifier) {
        RidesterTopAppBar(
            title = stringResource(R.string.bottomnavbar_lable_dashboard),
            modifier = Modifier,
            navigationIcon = {  },
            actions = {  }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
        ) {
            items(viewState.content) { item ->
                DashboardItem(
                    modifier = Modifier.fillParentMaxHeight(),
                    content = item
                )
            }
        }

    }
}