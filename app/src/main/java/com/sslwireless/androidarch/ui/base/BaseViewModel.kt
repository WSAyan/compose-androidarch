package com.sslwireless.androidarch.ui.base

import androidx.lifecycle.ViewModel
import com.sslwireless.androidarch.network.NetworkState
import com.sslwireless.androidarch.network.resolveError
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel : ViewModel() {
    fun <T> baseApiCall(networkState: NetworkState.Data<T>) = flow {
        emit(NetworkState.Loading)

        try {
            emit(networkState)
        } catch (e: Exception) {
            emit(e.resolveError())
        }
    }

}