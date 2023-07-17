package com.wsayan.androidarch.network.data.donors

import com.google.gson.annotations.SerializedName
import com.wsayan.androidarch.network.data.BaseResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class DonorsResponse(
    @SerializedName("data") val data: DonorsData? = null
) : BaseResponse()

