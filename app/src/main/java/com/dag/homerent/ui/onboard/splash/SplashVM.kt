package com.dag.homerent.ui.onboard.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.dag.homerent.base.ui.HomeRentViewModel
import com.dag.homerent.localdatastorage.preferencesdatastore.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    var preferencesDataStore: PreferencesDataStore,
) : HomeRentViewModel() {

    val splashState by mutableStateOf(SplashState())

    fun readWelcomeState() {
        viewModelScope.launch {
            preferencesDataStore.read(PreferencesDataStore.FIRST_LOGIN)
                .collect {
                    it?.let { firstLogin ->
                        if (splashState.welcomeScreenResult.value == WelcomeScreenResult.Wait) {
                            splashState.welcomeScreenResult.value = if (firstLogin)
                                WelcomeScreenResult.NotFirstTime else
                                WelcomeScreenResult.FirstTime
                        }
                    }
                }
        }
    }
}