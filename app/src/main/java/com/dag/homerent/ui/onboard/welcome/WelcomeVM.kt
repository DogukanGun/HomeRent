package com.dag.homerent.ui.onboard.welcome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.dag.homerent.base.ui.HomeRentViewModel
import com.dag.homerent.localdatastorage.preferencesdatastore.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeVM @Inject constructor(
    var preferencesDataStore: PreferencesDataStore
) : HomeRentViewModel() {

    val welcomeState by mutableStateOf(WelcomeState())

}