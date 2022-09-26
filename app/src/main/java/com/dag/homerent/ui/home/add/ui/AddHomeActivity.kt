package com.dag.homerent.ui.home.add.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.dag.homerent.R
import com.dag.homerent.base.HomeRentActivity
import com.dag.homerent.base.HomeRentViewState
import com.dag.homerent.component.homerentinput.HomeRentInput
import com.dag.homerent.component.homerentswitch.HomeRentSwitch
import com.dag.homerent.data.common.TextFieldType
import com.dag.homerent.databinding.ActivityAddhomeBinding
import com.dag.homerent.network.commonresponse.TextField
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
    }

    private val backButtonOnClickListener = View.OnClickListener {
        this.finish()
    }

    override fun handleState(viewState: HomeRentViewState) {
        when (viewState) {
            is AddHomeVS.PageContent -> {
                handleUI(viewState.pageResponse.textFields)
            }
        }
    }

    private fun handleUI(list: List<TextField>) {
        for (textField in list) {
            if (textField.type == TextFieldType.Switch) {
                val switchComponent = HomeRentSwitch(binding.textFieldWrapperLL.context)
                switchComponent.refresh(textField)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                switchComponent.layoutParams = layoutParams
                binding.textFieldWrapperLL.addView(switchComponent)
            } else {
                val textFieldComponent = HomeRentInput(binding.textFieldWrapperLL.context)
                textFieldComponent.refreshLayout(textField)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textFieldComponent.layoutParams = layoutParams
                binding.textFieldWrapperLL.addView(textFieldComponent)
            }
        }
    }
}