package com.funnyrider34rus.ridester.ui.dashboard

import com.funnyrider34rus.ridester.domain.model.DashboardContent

data class DashboardViewState(
    val content: List<DashboardContent> = emptyList(),
    val isLike: LikesStatus = LikesStatus.NONE,
    val isComment: Boolean = false
)

enum class LikesStatus {
    NONE, LIKE, UNLIKE
}
