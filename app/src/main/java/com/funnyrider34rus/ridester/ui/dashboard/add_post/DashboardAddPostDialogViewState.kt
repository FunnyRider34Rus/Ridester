package com.funnyrider34rus.ridester.ui.dashboard.add_post

import android.net.Uri
import androidx.core.net.toUri

data class DashboardAddPostDialogViewState(
    val image: Uri = "".toUri(),
    val body: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isDone: Boolean = false
)
