package com.funnyrider34rus.ridester.core.util

const val TAG = "Ridester"

sealed class Response<out T> {
    object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T?
    ) : Response<T>()

    data class Failure(
        val e: Exception?
    ) : Response<Nothing>()
}