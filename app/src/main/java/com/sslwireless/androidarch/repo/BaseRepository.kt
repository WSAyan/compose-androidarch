package com.sslwireless.androidarch.repo

import com.sslwireless.androidarch.network.NetworkState
import com.sslwireless.androidarch.network.resolveError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


abstract class BaseRepository {
    protected suspend fun <T : Any> handleNetworkCall(call: suspend () -> T): Flow<NetworkState<T>> =
        flow {
            emit(NetworkState.Loading)

            try {
                emit(NetworkState.Data(call()))
            } catch (e: Exception) {
                emit(e.resolveError())
            }
        }
}