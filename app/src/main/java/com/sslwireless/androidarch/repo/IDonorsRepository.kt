package com.sslwireless.androidarch.repo

import androidx.paging.PagingData
import com.sslwireless.androidarch.network.NetworkState
import com.sslwireless.androidarch.network.data.donors.Donor
import com.sslwireless.androidarch.network.data.donors.DonorsResponse
import kotlinx.coroutines.flow.Flow

interface IDonorsRepository {
    suspend fun fetchBloodDonors(page: Int, user_name: String): DonorsResponse

    fun fetchPaginatedDonorsList(user_name: String): Flow<PagingData<Donor>>

    suspend fun fetchConfigurations(): Flow<NetworkState<DonorsResponse>>
}