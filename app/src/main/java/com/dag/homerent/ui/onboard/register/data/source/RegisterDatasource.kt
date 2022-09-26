package com.dag.homerent.ui.onboard.register.data.source

import com.dag.homerent.injection.NetworkModule
import com.dag.homerent.network.HomerentService
import com.dag.homerent.ui.onboard.register.data.request.RegisterUserModel
import javax.inject.Inject
import javax.inject.Named

class RegisterDatasource @Inject constructor(
    @Named(NetworkModule.UNAUTHENTICATED_SERVICE) private val homerentService: HomerentService
){
    suspend fun register(registerUserModel: RegisterUserModel,userType:String) =
        homerentService.register(registerUserModel,userType)
}