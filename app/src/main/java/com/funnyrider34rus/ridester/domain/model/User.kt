package com.funnyrider34rus.ridester.domain.model

import com.google.type.LatLng

data class User(
    val uid: String? = null,
    val displayName: String? = null,
    val photoURL: String? = null,
    val userLatLng: LatLng? = null,
    val userStatus: UserOnlineStatus = UserOnlineStatus.OFFLINE
)

enum class UserOnlineStatus {
    OFFLINE, ONLINE
}
