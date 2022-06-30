package com.sslwireless.androidarch.network.data.donors

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Donor(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("company_name") val company_name: String? = null,
    @SerializedName("designation") val designation: String? = null,
    @SerializedName("emp_id") val emp_id: String? = null,
    @SerializedName("department") val department: String? = null,
    @SerializedName("date_of_birth") val date_of_birth: String? = null,
    @SerializedName("age") val age: Int? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("blood_group") val blood_group: String? = null,
    @SerializedName("cell_no") val cell_no: String? = null,
    @SerializedName("status") val status: Int? = null
) : Parcelable
