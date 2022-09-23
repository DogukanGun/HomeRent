package com.dag.homerent.ui.onboard.login.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    val username:String,
    val userType:String,
    val token:String
): Parcelable