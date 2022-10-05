package com.dag.homerent.dailogbox

import com.dag.homerent.base.ui.HomeRentViewState
import kotlinx.coroutines.suspendCancellableCoroutine

interface RepeatableViewEffect: HomeRentViewState {
    var onRepeatAction:((Boolean)->Unit)?
}

suspend fun HomeRentViewState.shouldRetry():Boolean = suspendCancellableCoroutine { cancellableContinuation ->
    if (this is RepeatableViewEffect){
        onRepeatAction = {
            cancellableContinuation.resume(it,{})
        }
    }else {
        cancellableContinuation.resume(false,{})
    }
}