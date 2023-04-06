package com.funnyrider34rus.ridester.domain.use_case.dashboard

import com.funnyrider34rus.ridester.core.util.addOrRemoveIfContains
import com.funnyrider34rus.ridester.domain.model.CurrentUser
import com.funnyrider34rus.ridester.domain.model.DashboardContent
import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository

class DashboardLikeClickUseCase(
    private val repository: DashboardContentRepository,
    private val currentUser: CurrentUser
) {
    suspend fun invoke(content: DashboardContent) {
        val transform = content.copy(
            likes = addOrRemoveIfContains(
                content.likes,
                currentUser.uid.toString()
            )
        )
        repository.update(data = transform)
    }
}