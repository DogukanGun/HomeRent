package com.dag.homerent.ui.home.add.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.dag.homerent.R
import com.dag.homerent.base.ui.HomeRentActivity
import com.dag.homerent.base.ui.HomeRentViewState
import com.dag.homerent.component.homerentinput.HomeRentInput
import com.dag.homerent.component.homerentswitch.HomeRentSwitch
import com.dag.homerent.component.photopicker.HomerentPhotoPicker
import com.dag.homerent.component.verticalrow.VerticalRow
import com.dag.homerent.component.verticalrow.VerticalRowItem
import com.dag.homerent.data.common.ContentPages
import com.dag.homerent.data.common.TextFieldType
import com.dag.homerent.databinding.ActivityAddhomeBinding
import com.dag.homerent.network.commonresponse.ActivityBodyResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddHomeActivity : HomeRentActivity<AddHomeVM, ActivityAddhomeBinding>() {
    override fun getHomeViewModel(): AddHomeVM = addHomeVM

    override fun getLayout(): Int? = R.layout.activity_addhome

    @Inject
    lateinit var addHomeVM: AddHomeVM

    @Inject
    lateinit var addHomeWatcher: AddHomeWatcher

    private lateinit var openDocumentActivityResultLauncher: ActivityResultLauncher<Array<String>>
    private val imagePath = "image/*"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.backButtonBTN.setOnClickListener(backButtonOnClickListener)
        binding.nextButtonBTN.setOnClickListener(nextButtonClickListener)
        registerActivityResultLauncher()
    }

    private fun registerActivityResultLauncher() {
        openDocumentActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.OpenDocument()) {
                Toast.makeText(this, "Saved : $it", Toast.LENGTH_SHORT).show()
            }
    }

    private val backButtonOnClickListener = View.OnClickListener {
        if (viewModel.step == 1) {
            this.finish()
        } else {
            ContentPages.values().find { item ->
                item.id == viewModel.step - 1
            }?.let {
                viewModel.decrementStep()
                binding.textFieldWrapperLL.removeAllViews()
                viewModel.getActivityBody(it)
            }
        }
    }

    private val nextButtonClickListener = View.OnClickListener {
        val contentPage = ContentPages.values().find { item ->
            item.id == viewModel.step + 1
        }?.let {
            viewModel.incrementStep()
            binding.textFieldWrapperLL.removeAllViews()
            viewModel.getActivityBody(it)
        }
        if (contentPage == null)
            addHomeVM.createNewHome()
    }

    override fun handleState(viewState: HomeRentViewState) {
        super.handleState(viewState)
        when (viewState) {
            is AddHomeVS.PageContent -> {
                handleUI(viewState.pageResponse)
            }
            AddHomeVS.HomeCreated -> {
                Toast.makeText(this, "Home is created", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleUI(pageResponse: ActivityBodyResponse) {
        binding.titleTV.text = pageResponse.title
        binding.pageTitleTV.text = pageResponse.pageName
        for (textField in pageResponse.textFields) {
            when (textField.type) {
                TextFieldType.Switch -> {
                    val switchComponent = HomeRentSwitch(binding.textFieldWrapperLL.context)
                    switchComponent.refresh(textField)
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    switchComponent.layoutParams = layoutParams
                    if (!switchComponent.switchState.hasActiveObservers()) {
                        switchComponent.switchState.observe(this, Observer {
                            addHomeWatcher.observeModelBoolean(textField, it)
                        })
                    }
                    binding.textFieldWrapperLL.addView(switchComponent)
                }
                TextFieldType.Text, TextFieldType.Numeric -> {
                    val textFieldComponent = HomeRentInput(binding.textFieldWrapperLL.context)
                    textFieldComponent.refreshLayout(textField)
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (!textFieldComponent.text.hasActiveObservers()) {
                        textFieldComponent.text.observe(this, Observer {
                            if (!it.isNullOrEmpty())
                                addHomeWatcher.observeModelText(textField, it)
                        })
                    }
                    textFieldComponent.layoutParams = layoutParams
                    binding.textFieldWrapperLL.addView(textFieldComponent)
                }
                TextFieldType.List -> {
                    val verticalRow = VerticalRow(this)
                    verticalRow.setRecyclerViewAdapter(textField) { item, key ->
                        handleTextViewListListener(item, key)
                    }
                    binding.textFieldWrapperLL.addView(verticalRow)
                }
                TextFieldType.Image -> {
                    val homerentPhotoPicker = HomerentPhotoPicker(this)
                    homerentPhotoPicker.layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    )
                    homerentPhotoPicker.homerentPhotoPickerListener = handleImageClickListener
                    binding.textFieldWrapperLL.addView(homerentPhotoPicker)
                }
            }
        }
    }

    private var handleImageClickListener = object : HomerentPhotoPicker.HomrentPhotoPickerListener {
        override fun imageClicked() {
            openDocumentActivityResultLauncher.launch(arrayOf(imagePath))
        }
    }

    private fun handleTextViewListListener(item: VerticalRowItem, key: String) {
        addHomeWatcher.observeModelList(
            listObject = item.text,
            key = key
        )
    }
}