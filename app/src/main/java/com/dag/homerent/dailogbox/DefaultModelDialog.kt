package com.dag.homerent.dailogbox

import androidx.annotation.StringRes
import com.dag.homerent.base.HomerentDialogBox

data class DefaultModelDialog(
    @StringRes override val titleRes:Int? = null,
    @StringRes override val messageRes: Int? = null,
    override val negativeButton: ModelDialogButton? = null,
    override val positiveButton: ModelDialogButton,
    override val isCancelable: Boolean = true,
    override val isIconVisible: Boolean = true,
    override val dialogPrimaryColor: HomerentDialogBox.DialogPrimaryColor = HomerentDialogBox.DialogPrimaryColor.Red,
): ModelDialog