package com.funnyrider34rus.ridester.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterTopAppBar

@Composable
fun ScreenProfile(modifier: Modifier) {
    Column(modifier = modifier) {
        RidesterTopAppBar(
            title = R.string.bottomnavbar_lable_profile,
            modifier = Modifier,
            navigationIcon = { },
            actions = { }
        )
    }
}