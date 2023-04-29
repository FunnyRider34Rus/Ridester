package com.funnyrider34rus.ridester.ui.dashboard.add_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.funnyrider34rus.ridester.core.util.Response
import com.funnyrider34rus.ridester.domain.model.DashboardContent
import com.funnyrider34rus.ridester.domain.model.DashboardType
import com.funnyrider34rus.ridester.domain.repository.DashboardAddPostRepository
import com.funnyrider34rus.ridester.domain.repository.DashboardContentRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardAddPostDialogViewModel @Inject constructor(
    private val storage: DashboardAddPostRepository,
    private val firestore: DashboardContentRepository,
    private val auth: FirebaseAuth
) : ViewModel() {
    private val _viewState = MutableStateFlow(DashboardAddPostDialogViewState())
    val viewState: StateFlow<DashboardAddPostDialogViewState> = _viewState

    fun onEvent(event:DashboardAddPostDialogEvent) = viewModelScope.launch(Dispatchers.IO) {
        when(event) {
            is DashboardAddPostDialogEvent.EnteredBody -> {
                _viewState.value = _viewState.value.copy(body = event.body, isError = false)
            }
            is DashboardAddPostDialogEvent.SelectedImage -> {
                _viewState.value = _viewState.value.copy(isLoading = true)
                val result = storage.addImageToFirebaseStorage(event.image)
                when (result) {
                    is Response.Failure -> TODO()
                    Response.Loading -> _viewState.value = _viewState.value.copy(isLoading = true)
                    is Response.Success -> _viewState.value = _viewState.value.copy(isLoading = false, image = result.data!!)
                }

            }
            is DashboardAddPostDialogEvent.ButtonDone -> {
                if (event.body.isBlank()) {
                    _viewState.value = _viewState.value.copy(isError = true)
                } else {
                    val mContent = DashboardContent(
                        uid = auth.currentUser?.uid,
                        body = _viewState.value.body,
                        image = _viewState.value.image.toString(),
                        type = DashboardType.POST
                    )
                    firestore.insert(mContent)
                    _viewState.value = _viewState.value.copy(isDone = true)
                }
            }
        }
    }
}