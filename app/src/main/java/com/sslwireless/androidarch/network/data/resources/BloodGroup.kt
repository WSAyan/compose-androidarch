package com.sslwireless.androidarch.network.data.resources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BloodGroup(
    @SerializedName("blood_group") val blood_group: String? = null
) : Parcelable
