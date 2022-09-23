package com.dag.homerent.ui.onboard.register.ui.password

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class PasswordState(
    var success:MutableState<Boolean> = mutableStateOf(false),
    var countDownValue:MutableState<Int> = mutableStateOf(20)
)
