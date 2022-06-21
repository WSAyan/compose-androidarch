package com.sslwireless.androidarch.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sslwireless.androidarch.network.NetworkState
import com.sslwireless.androidarch.network.resolveError
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel : ViewModel() {
    var showProgressBar by mutableStateOf(false)

    fun forceLogout() {

    }
}