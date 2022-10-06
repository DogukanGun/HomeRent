package com.dag.homerent.ui.onboard.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SplashState(
    var welcomeScreenResult: MutableState<WelcomeScreenResult> = mutableStateOf(WelcomeScreenResult.Wait),
    var welcomeScreenDone: MutableState<Boolean> = mutableStateOf(false)
)

sealed class WelcomeScreenResult {
    object FirstTime : WelcomeScreenResult()
    object Wait : WelcomeScreenResult()
    object NotFirstTime : WelcomeScreenResult()
}