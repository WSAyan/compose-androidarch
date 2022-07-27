package com.sslwireless.androidarch.ui.screens.splash


import androidx.lifecycle.*
import com.sslwireless.androidarch.repo.IBaseRepository
import com.sslwireless.androidarch.repo.donors.IDonorsRepository
import com.sslwireless.androidarch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    var donorsRepository: IDonorsRepository
) : BaseViewModel() {

    fun getResources() {
        viewModelScope.launch {
            donorsRepository.fetchResources().collect {
                handleUiState(it)
            }
        }
    }
}


