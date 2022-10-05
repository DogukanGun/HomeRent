package com.dag.homerent.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.dag.homerent.base.ext.onSuccess
import com.dag.homerent.base.ext.retryIf
import com.dag.homerent.base.ext.shouldRetry
import com.dag.homerent.errorhandling.DefaultErrorHandler
import com.dag.homerent.errorhandling.ErrorHandler
import com.dag.homerent.network.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class HomeRentViewModel:ViewModel() {

    var viewState = MutableLiveData<HomeRentViewState>()
    private val loading = MutableLiveData<Boolean>(false)

    protected fun <T> Flow<BaseResult<T>>.publishLoading() = onEach {
        updateLoading(it is BaseResult.Loading)
    }

    private fun updateLoading(isLoading: Boolean) {
        this.loading.value = isLoading
    }

    fun isLoading() = loading.distinctUntilChanged()

    private fun postViewEffect(effect: HomeRentViewState) {
        viewState.value = effect
    }

    private fun <T> Flow<BaseResult<T>>.handleErrorWith(
        errorHandler: ErrorHandler?
    ): Flow<BaseResult<T>> = retryIf { exception, _ ->
        val effect = errorHandler?.handle(exception)
        effect?.let {
            postViewEffect(it)
            it.shouldRetry()
        } ?: false
    }

    protected fun <T> Flow<T>.subscribe() = launchIn(viewModelScope)

    protected fun <T> Flow<BaseResult<T>>.subscribe(
        errorHandler: ErrorHandler
    ) = handleErrorWith(errorHandler)
        .subscribe()

    protected fun <T> Flow<BaseResult<T>>.subscribe(
        errorHandler: ErrorHandler? = DefaultErrorHandler(),
        onSuccess: (T?) -> Unit
    ) = handleErrorWith(errorHandler)
        .onSuccess {
            onSuccess.invoke(it)
        }
        .subscribe()

    protected fun <T> Flow<BaseResult<T>>.subscribeNotNull(
        errorHandler: ErrorHandler = DefaultErrorHandler(),
        onSuccess: (T) -> Unit
    ) = subscribe(errorHandler) { result ->
        result?.let {
            onSuccess.invoke(it)
        }
    }
}