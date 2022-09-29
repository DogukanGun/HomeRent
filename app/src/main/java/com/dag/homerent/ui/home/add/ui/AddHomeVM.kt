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

    val buttonText = ObservableField<String>()
    var step = 0

    init {
        incrementStep()
        getActivityBody(ContentPages.AddHome)
    }

    fun getActivityBody(contentPage: ContentPages) {
        if (step == ContentPages.values().size) {
            buttonText.set(
                resourceManager.getString(R.string.activity_addhome_nextbutton_state2)
            )
        } else if (step == 1) {
            buttonText.set(
                resourceManager.getString(R.string.activity_addhome_nextbutton)
            )
        }
        addHomeContentUseCase.observe(contentPage.name)
            .subscribeNotNull {
                viewState.postValue(
                    AddHomeVS.PageContent(it)
                )
            }
    }

    fun incrementStep() {
        step += 1
    }

    fun decrementStep() {
        step -= 1
    }
}