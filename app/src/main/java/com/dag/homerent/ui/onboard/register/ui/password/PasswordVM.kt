package com.dag.homerent.ui.onboard.register.ui.password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.dag.homerent.base.ui.HomeRentViewModel
import com.dag.homerent.ui.onboard.register.data.request.RegisterUserModel
import com.dag.homerent.ui.onboard.register.domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PasswordVM @Inject constructor(
    var registerUseCase: RegisterUseCase
) : HomeRentViewModel() {

    val passwordState by mutableStateOf(PasswordState())

    fun createUser(registerModel: RegisterUserModel,userType:String){
        registerUseCase.observe(
            RegisterUseCase.RegisterUseCaseParams(
                registerUserModel = registerModel,
                userType = userType
            )
        ).publishLoading()
            .subscribe { response->
                if (response?.id != 0){
                    passwordState.success.value = true
                }
            }

    }
}