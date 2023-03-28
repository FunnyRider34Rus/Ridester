package com.funnyrider34rus.ridester.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor() : ViewModel() {

    private val _viewState = MutableStateFlow(LogInViewState())
    val viewState: StateFlow<LogInViewState> = _viewState

    fun onEvent(event: LogInEvent) {
        when(event) {
            is LogInEvent.CheckBoxClick -> { _viewState.value = _viewState.value.copy(isCheck = !_viewState.value.isCheck) }
            is LogInEvent.ButtonClick -> {  }
        }
    }
}