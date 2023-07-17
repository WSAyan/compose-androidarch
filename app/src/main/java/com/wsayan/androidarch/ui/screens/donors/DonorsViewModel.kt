package com.wsayan.androidarch.ui.screens.donors

import androidx.paging.PagingData
import com.wsayan.androidarch.network.data.donors.Donor
import com.wsayan.androidarch.repo.donors.IDonorsRepository
import com.wsayan.androidarch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DonorsViewModel @Inject constructor(var donorsRepository: IDonorsRepository) :
    BaseViewModel() {

    fun getDonors(userName: String): Flow<PagingData<Donor>> =
        donorsRepository.fetchPaginatedDonorsList(userName)
}

