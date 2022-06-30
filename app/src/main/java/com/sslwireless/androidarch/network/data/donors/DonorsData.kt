package com.sslwireless.androidarch.network.data.donors

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DonorsData(
    @SerializedName("total") val total: Int? = null,
    @SerializedName("per_page") val per_page: Int? = null,
    @SerializedName("current_page") val current_page: Int? = null,
    @SerializedName("last_page") val last_page: Int? = null,
    @SerializedName("next_page_url") val next_page_url: String? = null,
    @SerializedName("prev_page_url") val prev_page_url: String? = null,
    @SerializedName("from") val from: Int? = null,
    @SerializedName("to") val to: Int? = null,
    @SerializedName("data") val data: List<Donor>? = null
) : Parcelable
