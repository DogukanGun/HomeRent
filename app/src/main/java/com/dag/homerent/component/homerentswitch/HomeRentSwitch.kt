package com.dag.homerent.component.homerentswitch

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.dag.homerent.R
import com.dag.homerent.databinding.ComponentHomeRentSwitchBinding
import com.dag.homerent.network.commonresponse.TextField

class HomeRentSwitch : LinearLayout {

    lateinit var binding: ComponentHomeRentSwitchBinding

    constructor(context: Context?) : super(context) {
        init(null, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, 0)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleRes)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        binding = DataBindingUtil
            .inflate(
                LayoutInflater.from(context),
                R.layout.component_home_rent_switch,
                this,
                true
            )
    }

    fun refresh(textField: TextField) {
        binding.waterBillTV.text = textField.title
    }
}