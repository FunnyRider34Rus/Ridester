package com.funnyrider34rus.ridester.di

import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository
import com.funnyrider34rus.ridester.domain.use_case.dashboard.DashboardUseCases
import com.funnyrider34rus.ridester.domain.use_case.dashboard.GetDashboardContentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideDashboardUseCase(dashboardContentRepository: DashboardContentRepository) = DashboardUseCases(
        getDashboardContent = GetDashboardContentUseCase(dashboardContentRepository)
    )
}