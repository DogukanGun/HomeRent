package com.dag.homerent.component.verticalrow

import com.dag.homerent.R
import com.dag.homerent.base.adapter.ListItem

data class VerticalRowItem(
    val text: String
) : ListItem(R.layout.item_vertical_row)