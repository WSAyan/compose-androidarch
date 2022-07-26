package com.sslwireless.androidarch.ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    var showProgressBar by mutableStateOf(false)

    fun forceLogout() {

    }
}