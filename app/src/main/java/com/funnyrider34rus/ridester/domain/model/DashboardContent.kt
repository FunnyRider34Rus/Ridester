package com.funnyrider34rus.ridester.domain.model

import com.google.firebase.Timestamp

data class DashboardContent(
    val key: String? = null,
    val timestamp: Timestamp? = null,
    val uid: String? = null,
    val title: String? = null,
    val body: String? = null,
    val image: String? = null,
    val likes: List<String>? = null,
    val type: DashboardType? = null
)

enum class DashboardType {
    NEWS, POST
}