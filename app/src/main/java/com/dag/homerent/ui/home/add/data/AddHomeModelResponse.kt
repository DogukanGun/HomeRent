package com.dag.homerent.ui.home.add.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddHomeModelResponse(
    var homeName: String = "",
    var homePhoto: String = "",
    var price: Double = 0.0,
    var bedroomCount: Int = 0,
    var propertyType: String = "",
    var size: Double = 0.0,
    var homeImages: List<HomeImagesDto> = emptyList(),
    @SerializedName("landlordAccountDto")
    var landlordAccount: LandlordAccount = LandlordAccount(),
    var facilities: MutableList<CreateFacilityRequests> = mutableListOf()
) : Parcelable

@Parcelize
data class HomeImagesDto(
    var image: String
) : Parcelable
