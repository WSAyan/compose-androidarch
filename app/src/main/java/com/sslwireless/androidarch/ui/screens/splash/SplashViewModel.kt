package com.sslwireless.androidarch.ui.screens.splash


import com.sslwireless.androidarch.repo.donors.IDonorsRepository
import com.sslwireless.androidarch.ui.base.BaseViewModel
import com.sslwireless.androidarch.ui.main.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(var donorsRepository: IDonorsRepository) :
    BaseViewModel() {
    suspend fun getResources() =
        donorsRepository.fetchResources()
}