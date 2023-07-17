package com.wsayan.androidarch.ui.data

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItemData(
    @IdRes var id: Int,
    @StringRes var label: Int? = null,
    @DrawableRes var drawable: Int? = null,
    var icon: ImageVector? = null,
    var isExpandableSearch: Boolean = false
)