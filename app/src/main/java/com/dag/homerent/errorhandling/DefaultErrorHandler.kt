package com.dag.homerent.errorhandling


import com.dag.homerent.R
import com.dag.homerent.base.ui.HomeRentViewState
import com.dag.homerent.dailogbox.createGenericDialog
import com.dag.homerent.dailogbox.createRepeatableDialog
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
                    networkConnectionDialog()
                }
            }
            else -> {null}
        }
    }
}

// TODO: Hata mesajları gösterilcek

fun networkConnectionDialog(): HomeRentViewState = createGenericDialog(
    titleRes = R.string.network_error_message_title,
    messageRes = R.string.network_error_message_text,
)