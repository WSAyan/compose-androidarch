package com.sslwireless.androidarch.network.data.donors

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sslwireless.androidarch.network.data.BaseResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class DonorsResponse(
    @SerializedName("data") val data: DonorsData? = null
) : BaseResponse()

