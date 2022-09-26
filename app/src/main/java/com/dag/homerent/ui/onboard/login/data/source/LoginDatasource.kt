package com.dag.homerent.ui.onboard.login.data.source

import com.dag.homerent.injection.NetworkModule
import com.dag.homerent.network.HomerentService
import com.dag.homerent.ui.onboard.login.data.request.LoginRequest
import javax.inject.Inject
import javax.inject.Named

class LoginDatasource @Inject constructor(
    @Named(NetworkModule.UNAUTHENTICATED_SERVICE) private val homerentService: HomerentService
){
    suspend fun login(loginRequest: LoginRequest) =
        homerentService.login(loginRequest)
}