package com.sslwireless.androidarch.network.data.resources

import com.google.gson.annotations.SerializedName
import com.sslwireless.androidarch.network.data.BaseResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResourcesResponse(
    @SerializedName("data") val data: ResourceData? = null
) : BaseResponse()

