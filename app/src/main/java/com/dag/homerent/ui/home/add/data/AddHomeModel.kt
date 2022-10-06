package com.dag.homerent.ui.home.add.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddHomeModel(
    var homeName: String = "",
    var homePhoto: String = "",
    var price: Double = 0.0,
    var bedroomCount: Int = 0,
    var propertyType: String = "",
    var size: Double = 0.0,
    var waterBillIncluded: Boolean = false,
    var electricityBillIncluded: Boolean = false,
    var message: String = "",
    var autoPay: Boolean = false,
    @SerializedName("createLandlordAccountRequest")
    var landlordAccount: LandlordAccount = LandlordAccount(),
    var createFacilityRequests: MutableList<CreateFacilityRequests> = mutableListOf(),
    var homeCoordinate: HomeCoordinate = HomeCoordinate()
) : Parcelable

@Parcelize
data class LandlordAccount(
    var accountNumber: String = "",
    var bankName: String = "",
    var walletNumber: String = ""
) : Parcelable

@Parcelize
data class CreateFacilityRequests(
    var facilityType: String = ""
) : Parcelable

@Parcelize
data class HomeCoordinate(
    var long: Double = 0.0,
    var lat: Double = 0.0
) : Parcelable