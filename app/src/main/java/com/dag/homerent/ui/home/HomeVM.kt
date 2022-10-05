package com.dag.homerent.ui.home

import androidx.lifecycle.viewModelScope
import com.dag.homerent.base.ui.HomeRentViewModel
import com.dag.homerent.localdatastorage.preferencesdatastore.PreferencesDataStore
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeVM @Inject constructor(
    var preferencesDataStore: PreferencesDataStore
) : HomeRentViewModel() {
    fun nextState() {
        viewModelScope.launch {
            preferencesDataStore.write(
                PreferencesDataStore.FIRST_LOGIN,
                true
            )
        }
    }
}