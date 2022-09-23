package com.dag.homerent.errorhandling


import com.dag.homerent.base.HomeRentViewState
import com.dag.homerent.network.*
import java.net.HttpURLConnection
import javax.inject.Inject

open class DefaultErrorHandler @Inject constructor() : ErrorHandler {

    override fun handle(throwable: Throwable): HomeRentViewState? {
        if (throwable !is HomeNetworkFailure) {
            return null
        }

        return when (throwable) {
            NetworkConnectionFailure -> networkConnectionDialog()
            UnauthenticatedFailure -> networkConnectionDialog()
            SocketTimeoutFailure, is UnexpectedFailure -> createGenericDialog()
            is ApiFailure -> {
                if (throwable.response.code() == HttpURLConnection.HTTP_GATEWAY_TIMEOUT) {
                    createRepeatableDialog()
                } else {
                    createGenericDialog()
                }
            }
            else -> {null}
        }
    }
}

// TODO: Hata mesajları gösterilcek

fun networkConnectionDialog():HomeRentViewState?{
    return null
}
fun createRepeatableDialog():HomeRentViewState?{
    return null
}
fun createGenericDialog():HomeRentViewState?{
    return null
}