package com.funnyrider34rus.ridester.ui.dashboard.add_post

import com.funnyrider34rus.ridester.ui.dashboard.DashboardEvent

interface DashboardAddPostDialogEvent {
    class EnteredTitle(val title: String) : DashboardAddPostDialogEvent
    class EnteredBody(val body: String) : DashboardAddPostDialogEvent
    class SelectedImage(val image: String): DashboardAddPostDialogEvent
}