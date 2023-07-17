package com.wsayan.androidarch.repo.donors

import androidx.paging.PagingData
import com.wsayan.androidarch.network.AppNetworkState
import com.wsayan.androidarch.network.data.donors.Donor
import com.wsayan.androidarch.network.data.donors.DonorsResponse
import com.wsayan.androidarch.network.data.resources.ResourcesResponse
import com.wsayan.androidarch.repo.IBaseRepository
import kotlinx.coroutines.flow.Flow

interface IDonorsRepository: IBaseRepository {
    suspend fun fetchBloodDonors(page: Int, user_name: String): DonorsResponse

    fun fetchPaginatedDonorsList(user_name: String): Flow<PagingData<Donor>>

    suspend fun fetchResources(): Flow<AppNetworkState<ResourcesResponse>>
}