package com.funnyrider34rus.ridester.ui.login

import com.google.firebase.auth.AuthCredential

sealed class LogInEvent {
    object CheckBoxClick : LogInEvent()
    class ButtonClick(val credential: AuthCredential) : LogInEvent()
    class Error(val errorMessage: String?) : LogInEvent()
}
