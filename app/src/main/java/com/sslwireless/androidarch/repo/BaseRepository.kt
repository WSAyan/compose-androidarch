package com.sslwireless.androidarch.repo

import com.sslwireless.androidarch.db.RoomHelper
import com.sslwireless.androidarch.network.AppNetworkState
import com.sslwireless.androidarch.network.IApiService
import com.sslwireless.androidarch.network.NetworkErrorExceptions
import com.sslwireless.androidarch.network.data.BaseResponse
import com.sslwireless.androidarch.network.data.resources.ResourcesResponse
import com.sslwireless.androidarch.network.resolveError
import com.sslwireless.androidarch.preference.PreferencesHelper
import com.sslwireless.androidarch.util.makeJson
import com.sslwireless.androidarch.util.makeObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository : IBaseRepository {
    abstract var apiService: IApiService
    abstract var preferencesHelper: PreferencesHelper
    abstract var roomHelper: RoomHelper

    protected suspend fun <T : Any> handleNetworkCall(callback: suspend () -> T): Flow<AppNetworkState<T>> =
        flow {
            emit(AppNetworkState.Loading)

            try {
                val networkCall = AppNetworkState.Data(callback())
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

    override fun destroyCache() {
        preferencesHelper.destroy()
    }

    override fun cacheResources(resourcesResponse: ResourcesResponse) {
        preferencesHelper.put("resources", resourcesResponse.makeJson())
    }

    override fun getCachedResources(): ResourcesResponse =
        preferencesHelper["resources", ""].makeObject(ResourcesResponse::class) as ResourcesResponse
}