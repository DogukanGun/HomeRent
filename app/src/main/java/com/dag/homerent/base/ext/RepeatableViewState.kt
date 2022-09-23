package com.dag.homerent.base.ext

import com.dag.homerent.base.HomeRentViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

interface RepeatableRentViewEffect : HomeRentViewState {

    var onRepeatAction: ((Boolean) -> Unit)?
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun HomeRentViewState.shouldRetry(): Boolean = suspendCancellableCoroutine { continuation ->
    if (this is RepeatableRentViewEffect) {
        onRepeatAction = {
            continuation.resume(it,null)
        }
    } else {
        continuation.resume(false,null)
    }
}