package com.dag.homerent.ui.home.add.ui

import androidx.databinding.ObservableField
import com.dag.homerent.R
import com.dag.homerent.base.HomeRentViewModel
import com.dag.homerent.base.ResourceManager
import com.dag.homerent.data.common.ContentPages
import com.dag.homerent.ui.home.add.domain.AddHomeContentUseCase
import javax.inject.Inject

class AddHomeVM @Inject constructor(
    private val resourceManager: ResourceManager,
    private val addHomeContentUseCase: AddHomeContentUseCase
) : HomeRentViewModel() {

    val stepCount = ObservableField<String>()
    private var step = 0

    init {
        incrementStep()
        getActivityBody()
    }

    fun getActivityBody() {
        addHomeContentUseCase.observe(
            ContentPages.AddHome.name
        ).subscribeNotNull {
            viewState.postValue(
                AddHomeVS.PageContent(it)
            )
        }
    }

    fun incrementStep() {
        step += 1
        val str = resourceManager.getString(
            R.string.add_home_section_title,
            listOf(step.toString(), "Other detail")
        )
        stepCount.set(str)
    }

    fun decrementStep() {
        step -= 1
    }
}