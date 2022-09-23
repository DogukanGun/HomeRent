package com.dag.homerent.dailogbox

import androidx.annotation.StringRes

data class ModelDialogButton(
    @StringRes val textRes: Int,
    val onClick:(()->Unit)? = null
)
