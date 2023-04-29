package com.funnyrider34rus.ridester.data.repository

import android.net.Uri
import com.funnyrider34rus.ridester.core.util.FIREBASE_STORAGE_MEDIA
import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.domain.repository.DashboardAddPostRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DashboardAddPostRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage
) : DashboardAddPostRepository {
    override suspend fun addImageToFirebaseStorage(imageUri: Uri): Response<Uri> {
        return try {
            val downloadUrl = storage.reference.child(FIREBASE_STORAGE_MEDIA).child(
                System.currentTimeMillis().toString()
            )
                .putFile(imageUri).await().storage.downloadUrl.await()

            Response.Success(downloadUrl)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}