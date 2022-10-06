package com.dag.homerent.component.photopicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.dag.homerent.R
import com.dag.homerent.databinding.ComponentHomerentPhotoPickerBinding

class HomerentPhotoPicker : ConstraintLayout {

    private lateinit var binding: ComponentHomerentPhotoPickerBinding
    var homerentPhotoPickerListener: HomrentPhotoPickerListener? = null

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
        initLayout(attrs, 0)
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
            R.layout.component_homerent_photo_picker,
            this,
            true
        )
        binding.imageView.scaleType = ImageView.ScaleType.FIT_XY
        binding.imageView.setOnClickListener(imageClickListener)
    }

    private val imageClickListener = View.OnClickListener {
        homerentPhotoPickerListener?.imageClicked()
    }

    interface HomrentPhotoPickerListener {
        fun imageClicked()
    }
}