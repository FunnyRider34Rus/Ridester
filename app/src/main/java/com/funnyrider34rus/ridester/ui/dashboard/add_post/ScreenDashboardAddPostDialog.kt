package com.funnyrider34rus.ridester.ui.dashboard.add_post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.funnyrider34rus.ridester.R
import com.funnyrider34rus.ridester.core.components.RidesterCenterTopAppBar
import com.funnyrider34rus.ridester.core.components.RidesterLoadingWidget
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScreenDashboardAddPostDialog(
    navigateToBack: () -> Unit,
    state: DashboardAddPostDialogViewState,
    onEvent: (DashboardAddPostDialogEvent) -> Unit
) {

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            if (imageUri == null) navigateToBack.invoke() else onEvent(DashboardAddPostDialogEvent.SelectedImage(imageUri))
        }

    LaunchedEffect(true) {
        galleryLauncher.launch("image/*")
    }

    Column {
        RidesterCenterTopAppBar(
            modifier = Modifier,
            title = R.string.screen_dashboard_add_post_appbar_title,
            navigationIcon = R.drawable.ic_button_back,
            actionIcon = R.drawable.ic_done,
            onNavigationClick = navigateToBack
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CoilImage(
                imageModel = { state.image },
                modifier = Modifier.weight(1f),
                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = state.body,
                    onValueChange = { onEvent(DashboardAddPostDialogEvent.EnteredBody(it)) },
                    modifier = Modifier.weight(1f).padding(TextFieldDefaults.textFieldWithoutLabelPadding()),
                    isError = state.isError,
                    trailingIcon = {
                        if (state.isError)
                            Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colorScheme.error)
                    },
                )
                IconButton(
                    onClick = { onEvent(DashboardAddPostDialogEvent.ButtonDone(state.image, state.body)) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_send_outline),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }

    if (state.isDone) navigateToBack.invoke()
    if (state.isLoading) RidesterLoadingWidget(modifier = Modifier.fillMaxSize())
}