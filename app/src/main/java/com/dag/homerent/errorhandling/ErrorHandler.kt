package com.dag.homerent.errorhandling

import com.dag.homerent.base.ui.HomeRentViewState


interface ErrorHandler {

    fun handle(throwable: Throwable): HomeRentViewState?
}