package com.dag.homerent.ui.onboard.login.data.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(
    val username:String,
    val password:String
): Parcelable