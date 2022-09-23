package com.dag.homerent.ui.onboard

import androidx.lifecycle.MutableLiveData

data class OnboardVS(
    var navigate:MutableLiveData<Boolean> = MutableLiveData(false)
)