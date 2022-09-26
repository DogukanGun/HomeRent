package com.dag.homerent.network.commonresponse

import android.os.Parcelable
import com.dag.homerent.data.common.TextFieldType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActivityBodyResponse(
    val pageName: String,
    val title: String,
    val textFields: List<TextField>
) : Parcelable

@Parcelize
data class TextField(
    val hint: String,
    val title: String,
    val type: TextFieldType
) : Parcelable