package com.dag.homerent.ui.onboard.register.data.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterUserModel(
    val username:String = "username1",
    var password:String = ""
):Parcelable
