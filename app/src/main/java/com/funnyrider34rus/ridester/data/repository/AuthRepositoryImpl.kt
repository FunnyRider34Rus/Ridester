package com.funnyrider34rus.ridester.data.repository

import com.funnyrider34rus.ridester.core.util.FIRESTORE_NODE_USERS
import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.domain.model.User
import com.funnyrider34rus.ridester.domain.model.UserOnlineStatus
import com.funnyrider34rus.ridester.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override fun isUserAuthenticatedInFirebase() = auth.currentUser != null

    override suspend fun signIn(credential: AuthCredential): Response<Boolean> {
        return try {
            Firebase.auth.signInWithCredential(credential).await()
            Response.Success(true)
        } catch (error: Exception) {
            Response.Failure(error)
        }
    }

    override suspend fun signOut(): Response<Boolean> {
        return try {
            auth.signOut()
            Response.Success(true)
        } catch (error: Exception) {
            Response.Failure(error)
        }
    }

    override suspend fun writeUserToDB(status: UserOnlineStatus): Response<Boolean> {
        return try {
            val user = User(
                uid = auth.currentUser?.uid,
                displayName = auth.currentUser?.displayName,
                photoURL = auth.currentUser?.photoUrl.toString(),
                userLatLng = null,
                userStatus = status
            )
            user.uid?.let {uid -> firestore.collection(FIRESTORE_NODE_USERS).document(uid).set(user).await() }
            Response.Success(true)
        } catch (error: Exception) {
            Response.Failure(error)
        }
    }
}