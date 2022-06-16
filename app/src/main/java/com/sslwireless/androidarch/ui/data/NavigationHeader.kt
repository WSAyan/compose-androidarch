package com.sslwireless.androidarch.ui.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NavigationHeader(
    var id: Int? = null,
    var title: String? = null,
    var subtitle: String? = null,
) : Parcelable

