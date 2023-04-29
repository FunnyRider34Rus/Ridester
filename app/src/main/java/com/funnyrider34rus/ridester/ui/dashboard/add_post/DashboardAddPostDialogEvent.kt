package com.funnyrider34rus.ridester.ui.dashboard.add_post

import android.net.Uri

interface DashboardAddPostDialogEvent {
    class EnteredBody(val body: String) : DashboardAddPostDialogEvent
    class SelectedImage(val image: Uri): DashboardAddPostDialogEvent
    class ButtonDone(val image: Uri, val body: String): DashboardAddPostDialogEvent
}