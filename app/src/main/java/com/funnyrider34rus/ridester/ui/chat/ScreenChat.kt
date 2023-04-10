package com.funnyrider34rus.ridester.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterTopAppBar

@Composable
fun ScreenChat(modifier: Modifier) {
    Column(modifier = modifier) {
        RidesterTopAppBar(
            title = R.string.bottomnavbar_lable_chat,
            modifier = Modifier,
            navigationIcon = { },
            actions = { }
        )
    }
}