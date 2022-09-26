package com.dag.homerent.ui.home.add.data

import com.dag.homerent.injection.NetworkModule
import com.dag.homerent.network.BaseRepository
import com.dag.homerent.network.HomerentService
import com.dag.homerent.network.getDataAsResult
import javax.inject.Inject
import javax.inject.Named

class AddHomeRepository @Inject constructor(
    @Named(NetworkModule.AUTHENTICATED_SERVICE) private val homerentService: HomerentService
) : BaseRepository() {
    fun getContent(pageName: String) = fetch {
        homerentService.getActivityBody(pageName).getDataAsResult()
    }
}