package com.funnyrider34rus.ridester.ui.login

sealed class LogInEvent {
    object CheckBoxClick : LogInEvent()
    object ButtonClick : LogInEvent()
}
