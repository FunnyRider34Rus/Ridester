package com.funnyrider34rus.ridester.data.model

import com.funnyrider34rus.ridester.domain.model.DashboardContent
import com.funnyrider34rus.ridester.domain.model.DashboardType
import com.google.firebase.Timestamp

data class DashboardContentDTO(
    val key: String? = null,
    val timestamp: Timestamp? = null,
    val uid: String? = null,
    val title: String? = null,
    val body: String? = null,
    val image: String? = null,
    val likes: List<String>? = null,
    val type: String? = null
)

fun DashboardContentDTO.toDashboardContent() = DashboardContent(
    key = key,
    timestamp = timestamp,
    uid = uid,
    title = title,
    body = body,
    image = image,
    likes = likes,
    type = if(type.equals(DashboardType.NEWS.toString())) DashboardType.NEWS else DashboardType.POST
)

fun DashboardContent.toDashboardContentDTO() = DashboardContentDTO(
    key = key,
    timestamp = timestamp,
    uid = uid,
    title = title,
    body = body,
    image = image,
    likes = likes,
    type = type.toString()
)