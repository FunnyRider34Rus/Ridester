package com.funnyrider34rus.ridester.ui.dashboard

interface DashboardEvent {
    object LikeClick: DashboardEvent
    object CommentClick: DashboardEvent
    object ContentClick: DashboardEvent
    object CreateClick: DashboardEvent
}