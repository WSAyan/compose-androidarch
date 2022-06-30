package com.sslwireless.androidarch.ui.screens.donors

import androidx.paging.PagingData
import com.sslwireless.androidarch.network.data.donors.Donor
import com.sslwireless.androidarch.repo.IDonorsRepository
import com.sslwireless.androidarch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DonorsViewModel @Inject constructor(var moviesRepo: IDonorsRepository) : BaseViewModel() {

    fun getDonors(userName: String): Flow<PagingData<Donor>> =
        moviesRepo.fetchPaginatedDonorsList(userName)
}

