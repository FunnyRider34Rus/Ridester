package com.funnyrider34rus.ridester.di

import com.funnyrider34rus.ridester.data.repository.AuthRepositoryImpl
import com.funnyrider34rus.ridester.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModules {
    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(auth)
}