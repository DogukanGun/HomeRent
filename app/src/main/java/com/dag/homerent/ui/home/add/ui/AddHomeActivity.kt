package com.dag.homerent.ui.home.add.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.dag.homerent.R
import com.dag.homerent.base.HomeRentActivity
import com.dag.homerent.base.HomeRentViewState
import com.dag.homerent.component.homerentinput.HomeRentInput
import com.dag.homerent.component.homerentswitch.HomeRentSwitch
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.backButtonBTN.setOnClickListener(backButtonOnClickListener)
        binding.nextButtonBTN.setOnClickListener(nextButtonClickListener)
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
        ContentPages.values().find { item ->
            item.id == viewModel.step + 1
        }?.let {
            viewModel.incrementStep()
            binding.textFieldWrapperLL.removeAllViews()
            viewModel.getActivityBody(it)
        }
    }

    override fun handleState(viewState: HomeRentViewState) {
        when (viewState) {
            is AddHomeVS.PageContent -> {
                handleUI(viewState.pageResponse)
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
                    binding.textFieldWrapperLL.addView(switchComponent)
                }
                TextFieldType.Text, TextFieldType.Numeric -> {
                    val textFieldComponent = HomeRentInput(binding.textFieldWrapperLL.context)
                    textFieldComponent.refreshLayout(textField)
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
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
            }
        }
    }



    private fun handleTextViewListListener(item: VerticalRowItem, key: String) {
        Toast.makeText(this, "Item is clicked:$item", Toast.LENGTH_LONG).show()
    }
}