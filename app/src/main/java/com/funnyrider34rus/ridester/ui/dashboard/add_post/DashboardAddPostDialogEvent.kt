package com.funnyrider34rus.ridester.ui.dashboard.add_post

interface DashboardAddPostDialogEvent {
    class EnteredBody(val body: String) : DashboardAddPostDialogEvent
    class SelectedImage(val image: String): DashboardAddPostDialogEvent
}