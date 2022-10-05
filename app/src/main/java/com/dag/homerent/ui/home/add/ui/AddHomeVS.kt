package com.dag.homerent.ui.home.add.ui

import com.dag.homerent.base.ui.HomeRentViewState
import com.dag.homerent.network.commonresponse.ActivityBodyResponse

sealed class AddHomeVS : HomeRentViewState {
    class PageContent(val pageResponse: ActivityBodyResponse) : AddHomeVS()
    object HomeCreated : AddHomeVS()
}
