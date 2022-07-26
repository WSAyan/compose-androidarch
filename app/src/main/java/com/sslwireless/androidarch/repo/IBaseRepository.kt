package com.sslwireless.androidarch.repo

import com.sslwireless.androidarch.network.data.resources.ResourcesResponse

interface IBaseRepository {
    fun cacheResources(resourcesResponse: ResourcesResponse)

    fun getCachedResources(): ResourcesResponse

    fun destroyCache()
}