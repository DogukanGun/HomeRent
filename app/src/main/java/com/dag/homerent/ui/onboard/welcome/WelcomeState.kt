package com.dag.homerent.ui.onboard.welcome

import androidx.lifecycle.MutableLiveData

data class WelcomeState(
    var nextState: MutableLiveData<Boolean> = MutableLiveData()
)