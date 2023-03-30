package com.funnyrider34rus.ridester.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _viewState = MutableStateFlow(LogInViewState())
    val viewState: StateFlow<LogInViewState> = _viewState

    fun onEvent(event: LogInEvent) {
        when(event) {
            is LogInEvent.CheckBoxClick -> { _viewState.value = _viewState.value.copy(isCheck = !_viewState.value.isCheck) }
            is LogInEvent.ButtonClick -> {
                viewModelScope.launch {
                    val response = repository.signIn(event.credential)
                    when(response) {
                        is Response.Success -> {
                            _viewState.value = _viewState.value.copy(isLoading = false, isUserAuth = true)
                        }
                        is Response.Failure -> {
                            _viewState.value = _viewState.value.copy(isError = true, error = response.e?.localizedMessage ?: "Unexpected error")
                        }
                        is Response.Loading -> {
                            _viewState.value = _viewState.value.copy(isLoading = true)
                        }
                    }


                }
            }
            is LogInEvent.Error -> {
                _viewState.value = _viewState.value.copy(isError = true, error = event.errorMessage ?: "Unexpected error")
            }
        }
    }
}