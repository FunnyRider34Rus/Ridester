package com.funnyrider34rus.ridester.ui.ride

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterTopAppBar

@Composable
fun ScreenRide(modifier: Modifier) {
    Column(modifier = modifier) {
        RidesterTopAppBar(
            title = R.string.bottomnavbar_lable_ride,
            modifier = Modifier,
            navigationIcon = { },
            actions = { }
        )
    }
}