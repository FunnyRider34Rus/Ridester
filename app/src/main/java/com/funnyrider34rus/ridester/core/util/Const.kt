package com.funnyrider34rus.ridester.core.util

const val TAG = "Ridester"

//Collection References
const val FIRESTORE_NODE_USERS = "users"
const val FIRESTORE_NODE_DASHBOARD = "dashboard"

//Dashboard fields
const val FIELD_DASHBOARD_KEY = "key"
const val FIELD_DASHBOARD_TIMESTAMP = "timestamp"
const val FIELD_DASHBOARD_UID = "uid"
const val FIELD_DASHBOARD_TITLE = "title"
const val FIELD_DASHBOARD_HEADER = "header"
const val FIELD_DASHBOARD_BODY = "body"
const val FIELD_DASHBOARD_IMAGE = "image"
const val FIELD_DASHBOARD_LIKES = "likes"
const val FIELD_DASHBOARD_TYPE = "type"

sealed class Response<out T> {
    object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T?
    ) : Response<T>()

    data class Failure(
        val e: Exception?
    ) : Response<Nothing>()
}