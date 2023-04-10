package com.funnyrider34rus.ridester.ui.dashboard

import com.funnyrider34rus.ridester.domain.model.DashboardContent

interface DashboardEvent {
    class LikeClick(val content: DashboardContent) : DashboardEvent
    object ContentClick : DashboardEvent
}