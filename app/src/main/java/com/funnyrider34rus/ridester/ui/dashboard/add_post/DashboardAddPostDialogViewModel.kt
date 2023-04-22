package com.funnyrider34rus.ridester.ui.dashboard.add_post

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardAddPostDialogViewModel @Inject constructor() : ViewModel() {
    private val _viewState = MutableStateFlow(DashboardAddPostDialogViewState())
    val viewState: StateFlow<DashboardAddPostDialogViewState> = _viewState

    fun onEvent(event:DashboardAddPostDialogEvent) {
        when(event) {
            is DashboardAddPostDialogEvent.EnteredBody -> {
                _viewState.value = _viewState.value.copy(body = event.body)
            }
            is DashboardAddPostDialogEvent.SelectedImage -> {

            }
        }
    }
}