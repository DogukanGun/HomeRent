package com.dag.homerent.ui.onboard.register.ui.phone

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.dag.homerent.base.HomeRentViewModel
import com.dag.homerent.data.TentType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneVM @Inject constructor(): HomeRentViewModel() {

    val phoneState by mutableStateOf(PhoneState())

    fun validatePhone(){
        if (phoneState.username.isBlank() ||
            phoneState.userType.isBlank()
        ){
            phoneState.error.value = ErrorState.WRONG_PHONE_NUMBER
        }else{
            phoneState.success.value = true
        }
    }

    fun getUserType(userId:Int):String{
        return TentType.values().filter { it.id == userId }[0].name
    }
}