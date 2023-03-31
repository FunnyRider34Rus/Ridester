package com.funnyrider34rus.ridester.domain.repository

import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.domain.model.DashboardContent
import kotlinx.coroutines.flow.Flow

interface DashboardContentRepository {
    suspend fun getDashboardContent(): Flow<Response<List<DashboardContent>>>
    suspend fun insert(data: DashboardContent): Response<Boolean>
    suspend fun update(data: DashboardContent): Response<Boolean>
    suspend fun delete(key: String?): Response<Boolean>
}