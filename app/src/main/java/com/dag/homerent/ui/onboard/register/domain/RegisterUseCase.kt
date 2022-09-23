package com.dag.homerent.ui.onboard.register.domain

import com.dag.homerent.base.domain.FlowUseCase
import com.dag.homerent.network.BaseResult
import com.dag.homerent.ui.onboard.register.data.RegisterRepository
import com.dag.homerent.ui.onboard.register.data.request.RegisterUserModel
import com.dag.homerent.ui.onboard.register.data.response.RegisterResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) :FlowUseCase<RegisterUseCase.RegisterUseCaseParams,BaseResult<RegisterResult>>(){
    override fun buildUseCase(params: RegisterUseCaseParams): Flow<BaseResult<RegisterResult>> =
        registerRepository.register(params.registerUserModel,params.userType)

    data class RegisterUseCaseParams(
        val registerUserModel: RegisterUserModel,
        val userType:String
    )
}