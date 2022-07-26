package com.sslwireless.androidarch.network.data.resources

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Department(
    @SerializedName("department_name") val department_name: String? = null
) : Parcelable
