package com.funnyrider34rus.ridester.di

import com.funnyrider34rus.ridester.data.repository.AuthRepositoryImpl
import com.funnyrider34rus.ridester.data.repository.DashboardAddPostRepositoryImpl
import com.funnyrider34rus.ridester.data.repository.DashboardContentRepositoryImpl
import com.funnyrider34rus.ridester.domain.repository.AuthRepository
import com.funnyrider34rus.ridester.domain.repository.DashboardAddPostRepository
import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModules {
    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthRepository =
        AuthRepositoryImpl(auth, firestore)

    @Provides
    @Singleton
    fun provideDashboardContentRepository(referenceLink: CollectionReference): DashboardContentRepository =
        DashboardContentRepositoryImpl(referenceLink)

    @Provides
    @Singleton
    fun provideDashboardAddPostRepository(storage: FirebaseStorage): DashboardAddPostRepository =
        DashboardAddPostRepositoryImpl(storage)
}