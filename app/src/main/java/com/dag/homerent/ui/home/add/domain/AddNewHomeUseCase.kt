package com.dag.homerent.ui.home.add.domain

import com.dag.homerent.base.domain.FlowUseCase
import com.dag.homerent.network.BaseResult
import com.dag.homerent.ui.home.add.data.AddHomeModel
import com.dag.homerent.ui.home.add.data.AddHomeModelResponse
import com.dag.homerent.ui.home.add.data.AddHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddNewHomeUseCase @Inject constructor(
    private val homeRepository: AddHomeRepository
) : FlowUseCase<AddHomeModel, BaseResult<AddHomeModelResponse>>() {
    override fun buildUseCase(params: AddHomeModel): Flow<BaseResult<AddHomeModelResponse>> {
        return homeRepository.createHome(params)
    }

}