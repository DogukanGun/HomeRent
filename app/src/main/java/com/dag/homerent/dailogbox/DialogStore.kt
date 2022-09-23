package com.dag.homerent.dailogbox

import com.dag.homerent.R
import com.dag.homerent.base.HomerentDialogBox


fun createRepeatableDialog(
    titleRes: Int = R.string.app_name,
    messageRes: Int = com.airbnb.lottie.R.string.search_menu_title,
    positiveButton: ModelDialogButton = ModelDialogButton(textRes = R.string.app_name),
    negativeButton: ModelDialogButton? = null,
    isCancelable: Boolean = false,
    isIconVisible: Boolean = true,
    dialogPrimaryColor: HomerentDialogBox.DialogPrimaryColor = HomerentDialogBox.DialogPrimaryColor.Red,
    repeatOnPositiveButtonClick: Boolean = true,
    repeatOnNegativeButtonClick: Boolean = false,
    repeatOnCancel: Boolean = false,
): RepeatableModelDialog = DefaultRepeatableModelDialog(
    titleRes = titleRes,
    messageRes = messageRes,
    negativeButton = negativeButton,
    positiveButton = positiveButton,
    isCancelable = isCancelable,
    isIconVisible = isIconVisible,
    dialogPrimaryColor = dialogPrimaryColor,
    repeatOnPositiveButtonClick = repeatOnPositiveButtonClick,
    repeatOnNegativeButtonClick = repeatOnNegativeButtonClick,
    repeatOnCancel = repeatOnCancel
)

fun createGenericDialog(
    titleRes: Int = R.string.app_name,
    messageRes: Int = com.airbnb.lottie.R.string.search_menu_title,
    positiveButton: ModelDialogButton = ModelDialogButton(textRes = R.string.app_name),
    negativeButton: ModelDialogButton? = null,
    isCancelable: Boolean = false,
    dialogPrimaryColor: HomerentDialogBox.DialogPrimaryColor = HomerentDialogBox.DialogPrimaryColor.Red
): ModelDialog = DefaultModelDialog(
    titleRes = titleRes,
    messageRes = messageRes,
    positiveButton = positiveButton,
    negativeButton = negativeButton,
    isCancelable = isCancelable,
    dialogPrimaryColor = dialogPrimaryColor
)