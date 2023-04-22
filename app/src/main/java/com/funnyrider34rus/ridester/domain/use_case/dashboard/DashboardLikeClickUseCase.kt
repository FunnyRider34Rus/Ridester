package com.funnyrider34rus.ridester.domain.use_case.dashboard

import com.funnyrider34rus.ridester.core.util.addOrRemoveIfContains
import com.funnyrider34rus.ridester.domain.model.DashboardContent
import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardLikeClickUseCase(
    private val repository: DashboardContentRepository
) {
    suspend fun invoke(content: DashboardContent) {
        val transform = content.copy(
            likes = addOrRemoveIfContains(
                content.likes,
                Firebase.auth.currentUser?.uid.toString()
            )
        )
        repository.update(data = transform)
    }
}