package com.funnyrider34rus.ridester.ui.dashboard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardVewModel @Inject constructor() : ViewModel() {
    private val _viewState = MutableStateFlow(DashboardViewState())
    val viewState: StateFlow<DashboardViewState> = _viewState

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