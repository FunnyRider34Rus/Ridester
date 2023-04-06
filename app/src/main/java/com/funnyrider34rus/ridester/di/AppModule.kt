package com.funnyrider34rus.ridester.di

import com.funnyrider34rus.ridester.domain.model.CurrentUser
import com.funnyrider34rus.ridester.domain.model.UserOnlineStatus
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCurrentUser(): CurrentUser = CurrentUser(
        uid = Firebase.auth.currentUser?.uid,
        displayName = Firebase.auth.currentUser?.displayName,
        photoURL = Firebase.auth.currentUser?.photoUrl.toString(),
        userLatLng = null,
        userStatus = UserOnlineStatus.OFFLINE
    )
}