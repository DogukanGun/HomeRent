package com.dag.homerent.component.verticalrow

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.dag.homerent.R
import com.dag.homerent.base.adapter.ListItem

data class VerticalRowItem(
    val text: String,
    @DrawableRes var backgroundId: Int = R.drawable.bg_item_vertical_row,
    @ColorRes var textColor: Int = R.color.black
) : ListItem(R.layout.item_vertical_row) {
    fun changeState() {
        if (backgroundId == R.drawable.bg_item_vertical_row) {
            backgroundId = R.drawable.dialog_box_button_background_orange
            textColor = R.color.white
        } else if (backgroundId == R.drawable.dialog_box_button_background_orange) {
            backgroundId = R.drawable.bg_item_vertical_row
            textColor = R.color.black
        }
    }
}