package com.dag.homerent.ui.onboard.register.data

import com.dag.homerent.network.BaseRepository
import com.dag.homerent.network.getDataAsResult
import com.dag.homerent.ui.onboard.register.data.request.RegisterUserModel
import com.dag.homerent.ui.onboard.register.data.source.RegisterDatasource
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerDatasource: RegisterDatasource
): BaseRepository(){
    fun register(registerUserModel: RegisterUserModel,userType:String) = fetch {
        registerDatasource.register(registerUserModel,userType).getDataAsResult()
    }
}