package com.wsayan.androidarch.ui.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
data class ImageBody(
    var id: Int? = null,
    var name: String? = null,
    var imageUri: Uri? = null,
    var imageFile: File? = null
) : Parcelable
