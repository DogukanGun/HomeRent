package com.dag.homerent.component.homerentlocationpicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.dag.homerent.R
import com.dag.homerent.databinding.ComponentHomerentLocationpickerBinding

class HomerentLocationPicker : ConstraintLayout {

    private lateinit var binding: ComponentHomerentLocationpickerBinding

    constructor(context: Context) : super(context) {
        initLayout(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initLayout(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initLayout(attrs, defStyleAttr)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initLayout(attrs, defStyleAttr)
    }

    private fun initLayout(attrs: AttributeSet?, defStyle: Int) {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.component__homerent_locationpicker,
            this,
            true
        )
    }
}