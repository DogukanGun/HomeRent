package com.dag.homerent.component.verticalrow

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.dag.homerent.R
import com.dag.homerent.base.adapter.BasicAdapter
import com.dag.homerent.base.adapter.ItemClickListener
import com.dag.homerent.base.adapter.basicAdapter
import com.dag.homerent.databinding.ComponentVerticalRowBinding
import com.dag.homerent.network.commonresponse.TextField

class VerticalRow : ConstraintLayout {
    private lateinit var binding: ComponentVerticalRowBinding
    private lateinit var recyclerViewAdapter: BasicAdapter<VerticalRowItem>

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr)
    }


    private fun init(attrs: AttributeSet?, defStyle: Int) {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.component_vertical_row,
            this,
            true
        )
    }

    fun setRecyclerViewAdapter(
        textField: TextField,
        handleTextViewListListener: (item: VerticalRowItem, key: String) -> Unit
    ) {
        val recyclerViewClickListener =
            ItemClickListener<VerticalRowItem> { position, item ->
                handleInsideClickListener(item)
                handleTextViewListListener(item, textField.key)
            }
        recyclerViewAdapter = basicAdapter {
            itemClickListener = recyclerViewClickListener
            itemLayoutId = R.layout.item_vertical_row
            list = convertStringToList(textField.hint).toMutableList()
        }
        binding.titleTV.text = textField.title
        binding.itemListRV.adapter = recyclerViewAdapter
        binding.itemListRV.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun handleInsideClickListener(item: VerticalRowItem) {
        item.changeState()
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun convertStringToList(listStr: String): List<VerticalRowItem> {
        var newList = listStr.replace("[", "")
        newList = newList.replace("]", "")
        return newList.split(",").map {
            VerticalRowItem(it)
        }
    }
}