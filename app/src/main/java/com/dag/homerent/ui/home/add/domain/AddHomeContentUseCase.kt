package com.dag.homerent.ui.home.add.domain

import com.dag.homerent.base.domain.FlowUseCase
import com.dag.homerent.network.BaseResult
import com.dag.homerent.network.commonresponse.ActivityBodyResponse
import com.dag.homerent.ui.home.add.data.AddHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddHomeContentUseCase @Inject constructor(
    private val addHomeRepository: AddHomeRepository
) : FlowUseCase<String, BaseResult<ActivityBodyResponse>>() {
    override fun buildUseCase(params: String): Flow<BaseResult<ActivityBodyResponse>> =
        addHomeRepository.getContent(params)
}