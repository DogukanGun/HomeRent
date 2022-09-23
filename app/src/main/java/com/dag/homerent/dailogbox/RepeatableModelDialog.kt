package com.dag.homerent.dailogbox

import com.dag.homerent.base.HomeRentViewState

interface RepeatableModelDialog: HomeRentViewState, RepeatableViewEffect, ModelDialog {
    val repeatOnPositiveButtonClick:Boolean
    val repeatOnNegativeButtonClick:Boolean
    val repeatOnCancel:Boolean
}