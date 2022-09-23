package com.dag.homerent.ui.onboard.login.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginState(
    var success: MutableState<Boolean> = mutableStateOf(false),
)