package com.dag.homerent.ui.onboard.register.ui.phone

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class PhoneState(
    var error:MutableState<ErrorState?> = mutableStateOf(null),
    var success: MutableState<Boolean> = mutableStateOf(false),
    var username:String = "",
    var userType:String = ""
)

enum class ErrorState{
    NETWORK,
    WRONG_PHONE_NUMBER,
}