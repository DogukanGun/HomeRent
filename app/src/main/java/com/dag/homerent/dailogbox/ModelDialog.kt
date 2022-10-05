package com.dag.homerent.dailogbox

import com.dag.homerent.base.ui.HomeRentViewState
import com.dag.homerent.base.ui.HomerentDialogBox


interface ModelDialog: HomeRentViewState {

    val titleRes:Int?
    val messageRes:Int?
    val negativeButton: ModelDialogButton?
    val positiveButton: ModelDialogButton
    val isCancelable:Boolean
    val isIconVisible: Boolean
    val dialogPrimaryColor: HomerentDialogBox.DialogPrimaryColor
}