package com.dag.homerent.ui.onboard.login.data

import com.dag.homerent.network.BaseRepository
import com.dag.homerent.network.getDataAsResult
import com.dag.homerent.ui.onboard.login.data.request.LoginRequest
import com.dag.homerent.ui.onboard.login.data.source.LoginDatasource
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDatasource: LoginDatasource
): BaseRepository(){
    fun login(loginRequest: LoginRequest) = fetch {
        loginDatasource.login(loginRequest).getDataAsResult()
    }
}