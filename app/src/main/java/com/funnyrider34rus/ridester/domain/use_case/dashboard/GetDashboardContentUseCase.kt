package com.funnyrider34rus.ridester.domain.use_case.dashboard

import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository

class GetDashboardContentUseCase(private val dashboardContentRepository: DashboardContentRepository) {
    suspend operator fun invoke() = dashboardContentRepository.getDashboardContent()
}