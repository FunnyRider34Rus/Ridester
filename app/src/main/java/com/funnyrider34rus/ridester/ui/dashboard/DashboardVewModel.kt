package com.funnyrider34rus.ridester.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.domain.use_case.dashboard.DashboardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardVewModel @Inject constructor(private val useCases: DashboardUseCases) : ViewModel() {
    private val _viewState = MutableStateFlow(DashboardViewState())
    val viewState: StateFlow<DashboardViewState> = _viewState

    init {
        getDashboardContent()
    }

    private fun getDashboardContent() = viewModelScope.launch {
        useCases.getDashboardContent.invoke().collect { result ->
            when(result) {
                is Response.Loading -> {
                    _viewState.value = _viewState.value.copy(isLoading = true)
                }
                is Response.Success -> {
                    _viewState.value = _viewState.value.copy(isLoading = false)
                    _viewState.value = _viewState.value.copy(content = result.data ?: emptyList())
                }
                is Response.Failure -> {
                    _viewState.value = _viewState.value.copy(isError = true)
                    _viewState.value = _viewState.value.copy(error = result.e?.localizedMessage ?: "Unexpected Error")
                }
            }
        }
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.ContentClick -> {

            }
            is DashboardEvent.LikeClick -> {

            }
            is DashboardEvent.CommentClick -> {

            }
        }
    }
}