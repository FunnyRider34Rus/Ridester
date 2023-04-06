package com.funnyrider34rus.ridester.domain.use_case.dashboard

data class DashboardUseCases(
    val getDashboardContent: DashboardGetContentUseCase,
    val likeClick: DashboardLikeClickUseCase
)
