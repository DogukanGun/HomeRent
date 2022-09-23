package com.dag.homerent.ui.onboard.login.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.dag.homerent.base.HomeRentViewModel
import com.dag.homerent.data.UserLogin
import com.dag.homerent.session.HomerentSessionManager
import com.dag.homerent.session.SessionKey
import com.dag.homerent.ui.onboard.login.data.request.LoginRequest
import com.dag.homerent.ui.onboard.login.domain.LoginUseCase
import com.dag.homerent.ui.onboard.register.domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginVM @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: HomerentSessionManager
) : HomeRentViewModel() {

    val loginState by mutableStateOf(LoginState())

    fun loginUser(loginRequest: LoginRequest) {
        loginUseCase.observe(
            loginRequest
        ).publishLoading()
            .subscribe {
                it?.let {
                    sessionManager.addData(SessionKey.TOKEN.name, it.token)
                    loginState.success.value = true
                }
            }
    }
}