package com.funnyrider34rus.ridester.ui.login

data class LogInViewState(
    val isCheck: Boolean = false,
    val isUserAuth: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: String = ""
)
