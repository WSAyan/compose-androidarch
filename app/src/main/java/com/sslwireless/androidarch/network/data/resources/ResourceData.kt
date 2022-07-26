package com.sslwireless.androidarch.network.data.resources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResourceData(
    @SerializedName("blood_groups") val blood_groups: List<BloodGroup>? = null,
    @SerializedName("companies") val companies: List<Company>? = null
) : Parcelable
