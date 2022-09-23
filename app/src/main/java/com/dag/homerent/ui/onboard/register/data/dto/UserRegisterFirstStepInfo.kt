package com.dag.homerent.ui.onboard.register.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRegisterFirstStepInfo(
    val username:String,
    val userType:String
): Parcelable
