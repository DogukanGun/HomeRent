package com.dag.homerent.ui.home.add.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddHomeModel(
    var homeName: String,
    var homePhoto: String,
    var price: Double,
    var bedroomCount: Int,
    var propertyType: String,
    var size: Double,
    var waterBillIncluded: Boolean,
    var electricityBillIncluded: Boolean,
    var message: String,
    var autoPay: Boolean
) : Parcelable

@Parcelize
data class LandlordAccount(
    var accountNumber: String,
    var bankName: String,
    var walletNumber: String
) : Parcelable