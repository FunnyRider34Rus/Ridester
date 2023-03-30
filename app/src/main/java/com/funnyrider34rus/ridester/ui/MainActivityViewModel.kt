package com.funnyrider34rus.ridester.ui

import androidx.lifecycle.ViewModel
import com.funnyrider34rus.ridester.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    val isUserAuthenticated get() = repository.isUserAuthenticatedInFirebase()
}