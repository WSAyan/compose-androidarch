package com.sslwireless.androidarch.network.data.resources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    @SerializedName("company_name") val company_name : String? = null,
    @SerializedName("departments") val departments : List<Department>? = null
) : Parcelable