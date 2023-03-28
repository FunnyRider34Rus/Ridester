package com.funnyrider34rus.ridester.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor() : ViewModel() {

    private val _viewState = MutableStateFlow(LogInViewState())
    val viewState: StateFlow<LogInViewState> = _viewState

    fun onEvent(event: LogInEvent) {
        when(event) {
            is LogInEvent.CheckBoxClick -> { _viewState.value = _viewState.value.copy(isCheck = !_viewState.value.isCheck) }
            is LogInEvent.ButtonClick -> {
                viewModelScope.launch {
                    try {
                        _viewState.value = _viewState.value.copy(isLoading = true)
                        Firebase.auth.signInWithCredential(event.credential).await()
                        _viewState.value = _viewState.value.copy(isLoading = false, isUserAuth = true)
                    } catch (e: Exception) {
                        _viewState.value = _viewState.value.copy(isError = true, error = e.localizedMessage)
                    }
                }
            }
            is LogInEvent.Error -> {
                _viewState.value = _viewState.value.copy(isError = true, error = event.errorMessage.toString())
            }
        }
    }
}