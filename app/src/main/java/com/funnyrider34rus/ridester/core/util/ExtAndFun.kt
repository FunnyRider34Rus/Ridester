package com.funnyrider34rus.ridester.core.util

import com.funnyrider34rus.ridester.domain.model.User
import com.funnyrider34rus.ridester.ui.dashboard.LikesStatus
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Locale

suspend fun getUserByUid(firestore: FirebaseFirestore, uid: String) =
    firestore.collection(FIRESTORE_NODE_USERS).document(uid).get().await()
        .toObject(User::class.java)

fun timestampToDate(timestamp: Timestamp?): String {
    val long = (timestamp?.seconds?.times(1000) ?: 0) + (timestamp?.nanoseconds?.div(1000000) ?: 0)
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return simpleDateFormat.format(long)
}

fun addOrRemoveIfContains(source: List<String>?, value: String): List<String>? {
    var modified = source?.toMutableList()
    if (source != null) {
        if (source.contains(value)) {
            modified?.remove(value)
        } else {
            modified?.add(value)
        }
    } else {
        modified = mutableListOf(value)
    }
    return modified
}

fun getLikeStatus(list: List<String>?): LikesStatus {
    var result: LikesStatus
    result = if (list.isNullOrEmpty()) LikesStatus.NONE else LikesStatus.UNLIKE
    if (list?.contains(Firebase.auth.currentUser?.uid) == true) result = LikesStatus.LIKE
    return result
}