package com.funnyrider34rus.ridester.ui.dashboard

import com.funnyrider34rus.ridester.domain.model.DashboardContent

data class DashboardViewState(
    val content: List<DashboardContent> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: String = "",
    val isLike: LikesStatus = LikesStatus.NONE,
    val isComment: Boolean = false,
    val isBodyExpand: Boolean = false
)

enum class LikesStatus {
    NONE, LIKE, UNLIKE
}
