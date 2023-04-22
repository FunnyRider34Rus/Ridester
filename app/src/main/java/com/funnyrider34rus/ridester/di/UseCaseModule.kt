package com.funnyrider34rus.ridester.di

import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository
import com.funnyrider34rus.ridester.domain.use_case.dashboard.DashboardUseCases
import com.funnyrider34rus.ridester.domain.use_case.dashboard.DashboardGetContentUseCase
import com.funnyrider34rus.ridester.domain.use_case.dashboard.DashboardLikeClickUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideDashboardUseCase(dashboardContentRepository: DashboardContentRepository) = DashboardUseCases(
        getDashboardContent = DashboardGetContentUseCase(dashboardContentRepository),
        likeClick = DashboardLikeClickUseCase(dashboardContentRepository)
    )
}