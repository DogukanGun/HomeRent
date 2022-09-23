package com.dag.homerent.ui.onboard.register.data.source

import com.dag.homerent.network.HomerentService
import com.dag.homerent.ui.onboard.register.data.request.RegisterUserModel
import javax.inject.Inject

class RegisterDatasource @Inject constructor(
    private val homerentService: HomerentService
){
    suspend fun register(registerUserModel: RegisterUserModel,userType:String) =
        homerentService.register(registerUserModel,userType)
}