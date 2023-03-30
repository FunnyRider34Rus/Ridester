package com.funnyrider34rus.ridester.domain.repository

import com.google.firebase.auth.AuthCredential

interface AuthRepository {
    suspend fun isUserAuthenticatedInFirebase(): Boolean
    suspend fun signIn(credential: AuthCredential): Boolean
    suspend fun signOut(): Boolean
}