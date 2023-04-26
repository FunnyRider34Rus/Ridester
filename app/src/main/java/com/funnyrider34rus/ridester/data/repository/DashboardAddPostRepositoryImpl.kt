package com.funnyrider34rus.ridester.data.repository

import android.net.Uri
import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.domain.repository.DashboardAddPostRepository
import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class DashboardAddPostRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val repository: DashboardContentRepository
): DashboardAddPostRepository {
    override suspend fun addImageToFirebaseStorage(imageUri: Uri): Response<Uri> {
        TODO("Not yet implemented")
    }

    override suspend fun addImageUrlToFirestore(downloadUrl: Uri): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getImageUrlFromFirestore(): Response<String> {
        TODO("Not yet implemented")
    }
}