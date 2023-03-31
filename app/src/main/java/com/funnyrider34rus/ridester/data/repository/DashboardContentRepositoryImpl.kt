package com.funnyrider34rus.ridester.data.repository

import com.funnyrider34rus.ridester.core.util.FIELD_DASHBOARD_TIMESTAMP
import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.data.model.DashboardContentDTO
import com.funnyrider34rus.ridester.data.model.toDashboardContent
import com.funnyrider34rus.ridester.domain.model.DashboardContent
import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DashboardContentRepositoryImpl @Inject constructor(private val referenceLink: CollectionReference) :
    DashboardContentRepository {
    override suspend fun getDashboardContent(): Flow<Response<List<DashboardContent>>> = callbackFlow {
        val snapshotListener = referenceLink.orderBy(FIELD_DASHBOARD_TIMESTAMP, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                val response = if (value != null) {
                    val content = value.toObjects(DashboardContentDTO::class.java)
                    Response.Success(content.map { item -> item.toDashboardContent() })
                } else {
                    Response.Failure(error)
                }
                trySend(response)
            }
        awaitClose { snapshotListener.remove() }
    }

    override suspend fun insert(data: DashboardContent): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun update(data: DashboardContent): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(key: String?): Response<Boolean> {
        TODO("Not yet implemented")
    }
}