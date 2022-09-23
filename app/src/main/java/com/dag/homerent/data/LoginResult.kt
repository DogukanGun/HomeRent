package com.dag.homerent.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResult(
    var token:String = "",
    var username:String = "",
    var userType:String = ""
): Parcelable