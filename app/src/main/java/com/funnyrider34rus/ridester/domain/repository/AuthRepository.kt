package com.funnyrider34rus.ridester.domain.repository

import com.funnyrider34rus.ridester.core.util.Response
import com.google.firebase.auth.AuthCredential

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
    suspend fun signIn(credential: AuthCredential): Response<Boolean>
    suspend fun signOut(): Response<Boolean>
}