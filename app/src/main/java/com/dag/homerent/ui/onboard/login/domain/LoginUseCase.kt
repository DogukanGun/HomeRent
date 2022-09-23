package com.dag.homerent.ui.onboard.login.domain

import com.dag.homerent.base.domain.FlowUseCase
import com.dag.homerent.network.BaseRepository
import com.dag.homerent.network.BaseResult
import com.dag.homerent.ui.onboard.login.data.LoginRepository
import com.dag.homerent.ui.onboard.login.data.request.LoginRequest
import com.dag.homerent.ui.onboard.login.data.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): FlowUseCase<LoginRequest,BaseResult<LoginResponse>>() {
    override fun buildUseCase(params: LoginRequest): Flow<BaseResult<LoginResponse>> =
        loginRepository.login(params)

}