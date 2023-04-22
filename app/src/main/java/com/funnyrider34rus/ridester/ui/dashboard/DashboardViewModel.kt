package com.funnyrider34rus.ridester.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.domain.use_case.dashboard.DashboardUseCases
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCases: DashboardUseCases
) : ViewModel() {
    private val _viewState = MutableStateFlow(DashboardViewState())
    val viewState: StateFlow<DashboardViewState> = _viewState

    init {
        getDashboardContent()
    }

    private fun getDashboardContent() = viewModelScope.launch {
        useCases.getDashboardContent.invoke().collect { result ->
            when (result) {
                is Response.Loading -> {
                    _viewState.value = _viewState.value.copy(isLoading = true)
                }
                is Response.Success -> {
                    _viewState.value = _viewState.value.copy(isLoading = false)
                    _viewState.value = _viewState.value.copy(content = result.data ?: emptyList())
                }
                is Response.Failure -> {
                    _viewState.value = _viewState.value.copy(isError = true)
                    _viewState.value = _viewState.value.copy(
                        error = result.e?.localizedMessage ?: "Unexpected Error"
                    )
                }
            }
        }
    }

    fun getLikeStatus(list: List<String>?): LikesStatus {
        var result: LikesStatus
        result = if (list.isNullOrEmpty()) LikesStatus.NONE else LikesStatus.UNLIKE
        if (list?.contains(Firebase.auth.currentUser?.uid) == true) result = LikesStatus.LIKE
        return result
    }

    fun onEvent(event: DashboardEvent) = viewModelScope.launch {
        when (event) {
            is DashboardEvent.ContentClick -> {
                _viewState.value = _viewState.value.copy(isBodyExpand = !_viewState.value.isBodyExpand)
            }
            is DashboardEvent.LikeClick -> {
                useCases.likeClick.invoke(event.content)
            }
        }
    }
}