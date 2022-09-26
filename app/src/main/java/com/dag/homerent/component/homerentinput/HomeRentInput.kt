package com.dag.homerent.component.homerentinput

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dag.homerent.R
import com.dag.homerent.databinding.ComponentHomeRentInputBinding
import com.dag.homerent.network.commonresponse.TextField


class HomeRentInput : LinearLayout {

    private lateinit var binding: ComponentHomeRentInputBinding

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
        init(attrs, defStyleAttr)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        binding = DataBindingUtil
            .inflate(
                LayoutInflater.from(context),
                R.layout.component_home_rent_input,
                this,
                true
            )
        binding.textET.addTextChangedListener(textWatcher)
        attrs?.getAttributeIntValue(R.styleable.HomeRentInput_inputTitle, -1)?.let {
            if (it != -1) {
                binding.titleTV.setText(it)
            }
        }
        attrs?.getAttributeIntValue(R.styleable.HomeRentInput_inputType, -1)?.let {
            if (it != -1) {
                binding.textET.inputType = InputType.TYPE_CLASS_TEXT or it
            }
        }
    }

    fun refreshLayout(textField: TextField) {
        binding.apply {
            titleTV.text = textField.hint
            textET.hint = textField.hint
            try {
                textET.inputType = InputType.TYPE_CLASS_TEXT or
                        HomerentInputType.valueOf(textField.type.name).value
            } catch (e: Exception) {
                // TODO: logla
            }
        }
    }

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            p0?.toString()?.let {
                _text.postValue(it)
            }
        }

    }
}