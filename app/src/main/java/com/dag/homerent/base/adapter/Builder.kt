package com.dag.homerent.base.adapter

import androidx.recyclerview.widget.DiffUtil

@AdapterDsl
class Builder<T> {
    var itemLayoutId: Int = 0
    var itemClickListener: ItemClickListener<T>? = null
    var itemSelectionEnabled: Boolean = false
    var list:MutableList<T> = mutableListOf()
}

/**
 * multiple types of rows' adapter
 */
fun <T : ListItem> listAdapter(builder: Builder<T>.() -> Unit): ListAdapter<T> {
    val b = Builder<T>()
    b.builder()
    return ListAdapter.createWith(b)
}

/**
 * single type of row'sadapter
 */
fun <T> basicAdapter(builder: Builder<T>.() -> Unit): BasicAdapter<T> {
    val b = Builder<T>()
    b.builder()
    return BasicAdapter.createWith(b)
}

@DslMarker
annotation class AdapterDsl
