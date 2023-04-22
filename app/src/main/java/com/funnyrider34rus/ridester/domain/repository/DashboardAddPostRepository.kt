package com.funnyrider34rus.ridester.domain.repository

import android.net.Uri
import com.funnyrider34rus.ridester.core.util.Response

interface DashboardAddPostRepository {
    suspend fun addImageToFirebaseStorage(imageUri: Uri): Response<Uri>
    suspend fun addImageUrlToFirestore(downloadUrl: Uri): Response<Boolean>
    suspend fun getImageUrlFromFirestore(): Response<String>
}