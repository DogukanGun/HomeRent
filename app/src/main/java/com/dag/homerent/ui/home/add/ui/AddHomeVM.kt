package com.dag.homerent.ui.home.add.ui

import androidx.databinding.ObservableField
import com.dag.homerent.R
import com.dag.homerent.base.ResourceManager
import com.dag.homerent.base.ui.HomeRentViewModel
import com.dag.homerent.data.common.ContentPages
import com.dag.homerent.ui.home.add.domain.AddHomeContentUseCase
import com.dag.homerent.ui.home.add.domain.AddNewHomeUseCase
import javax.inject.Inject

class AddHomeVM @Inject constructor(
    private val resourceManager: ResourceManager,
    private val addHomeContentUseCase: AddHomeContentUseCase,
    private val addNewHomeUseCase: AddNewHomeUseCase,
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
            getNewPage(contentPage)
        } else if (step <= ContentPages.values().size) {
            buttonText.set(
                resourceManager.getString(R.string.activity_addhome_nextbutton)
            )
            getNewPage(contentPage)
        }
    }

    fun createNewHome() {
        addNewHomeUseCase.observe(
            AddHomeWatcher.addHomeRequestModel
        ).publishLoading()
            .subscribeNotNull {
                viewState.postValue(
                    AddHomeVS.HomeCreated
                )
            }
    }

    private fun getNewPage(contentPage: ContentPages) {
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