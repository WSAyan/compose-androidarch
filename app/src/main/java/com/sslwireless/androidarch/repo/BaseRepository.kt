package com.sslwireless.androidarch.repo

import com.sslwireless.androidarch.network.NetworkErrorExceptions
import com.sslwireless.androidarch.network.NetworkState
import com.sslwireless.androidarch.network.data.BaseResponse
import com.sslwireless.androidarch.network.resolveError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


abstract class BaseRepository {
    protected suspend fun <T : Any> handleNetworkCall(callback: suspend () -> T): Flow<NetworkState<T>> =
        flow {
            emit(NetworkState.Loading)

            try {
                val networkCall = NetworkState.Data(callback())
                val baseData = networkCall.data as BaseResponse
                when (baseData.code) {
                    200, 201 -> {
                        emit(networkCall)
                    }
                    else -> {
                        if (!baseData.message.isNullOrEmpty()) {
                            throw NetworkErrorExceptions(
                                errorCode = baseData.code ?: -1,
                                errorBody = baseData,
                                errorMessage = baseData.message,
                                unauthorized = baseData.code == 401
                            )
                        }

                        throw NetworkErrorExceptions(
                            errorCode = baseData.code ?: -1,
                            errorBody = baseData,
                            unauthorized = baseData.code == 401
                        )
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()

                emit(e.resolveError())
            }
        }
}