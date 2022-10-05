package com.dag.homerent.ui.onboard.login.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.dag.homerent.base.HeaderManager
import com.dag.homerent.base.ui.HomeRentViewModel
import com.dag.homerent.session.HomerentSessionManager
import com.dag.homerent.session.SessionKey
import com.dag.homerent.ui.onboard.login.data.request.LoginRequest
import com.dag.homerent.ui.onboard.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: HomerentSessionManager,
    private val headerManager: HeaderManager
) : HomeRentViewModel() {

    val loginState by mutableStateOf(LoginState())

    fun loginUser(loginRequest: LoginRequest) {
        loginUseCase.observe(
            loginRequest
        ).publishLoading()
            .subscribe {
                it?.let {
                    headerManager.putHeader(SessionKey.Authorization.name, it.token)
                    headerManager.putHeader(SessionKey.accept.name, "*/*")
                    loginState.success.value = true
                }
            }
    }
}