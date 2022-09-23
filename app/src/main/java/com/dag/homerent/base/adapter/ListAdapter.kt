package com.dag.homerent.base.adapter

import androidx.recyclerview.widget.DiffUtil

class ListAdapter<T : ListItem> private constructor(
    itemClickListener: ItemClickListener<T>?,
    selectionEnabled: Boolean,
    private val list: MutableList<T>
) : BasicAdapter<T>(0, itemClickListener, selectionEnabled,list) {

    override fun getItemViewType(position: Int): Int {
        return list[position].layoutId
    }

    override fun getItemId(position: Int) = position.toLong()

    companion object {
        fun <T : ListItem> createWith(builder: Builder<T>): ListAdapter<T> {

            return ListAdapter(
                builder.itemClickListener,
                builder.itemSelectionEnabled,
                builder.list
            )
        }
    }
}

