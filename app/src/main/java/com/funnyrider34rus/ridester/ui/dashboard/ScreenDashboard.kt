package com.funnyrider34rus.ridester.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterTopAppBar

@Composable
fun ScreenDashboard(navController: NavController, modifier: Modifier) {
    Column(modifier = modifier) {
        RidesterTopAppBar(
            titleRes = R.string.bottomnavbar_lable_dashboard,
            modifier = Modifier,
            navigationIcon = {  },
            actions = {  }
        )

    }
}