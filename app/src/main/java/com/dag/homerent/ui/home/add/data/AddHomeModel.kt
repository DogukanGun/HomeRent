package com.dag.homerent.ui.home.add.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
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
    var autoPay: Boolean,
    var homeImagesAsBase64: List<String>,
    @SerializedName("createLandlordAccountRequest")
    var landlordAccount: LandlordAccount,
    var createFacilityRequests: CreateFacilityRequests
) : Parcelable

@Parcelize
data class LandlordAccount(
    var accountNumber: String,
    var bankName: String,
    var walletNumber: String
) : Parcelable

@Parcelize
data class CreateFacilityRequests(
    var facilityType: String
) : Parcelable