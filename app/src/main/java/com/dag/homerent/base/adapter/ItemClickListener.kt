package com.dag.homerent.base.adapter

fun interface ItemClickListener<T> {

    fun onClick(position: Int, item: T)
}
