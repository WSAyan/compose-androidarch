package com.wsayan.androidarch.repo

import com.wsayan.androidarch.network.data.resources.ResourcesResponse

interface IBaseRepository {
    fun cacheResources(resourcesResponse: ResourcesResponse)

    fun getCachedResources(): ResourcesResponse

    fun destroyCache()
}