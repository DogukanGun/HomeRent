package com.dag.homerent.ui.onboard.login.data.source

import com.dag.homerent.network.HomerentService
import com.dag.homerent.ui.onboard.login.data.request.LoginRequest
import javax.inject.Inject

class LoginDatasource @Inject constructor(
    private val homerentService: HomerentService
){
    suspend fun login(loginRequest: LoginRequest) =
        homerentService.login(loginRequest)
}