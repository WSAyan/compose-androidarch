package com.sslwireless.androidarch.ui.screens.splash

import com.sslwireless.androidarch.repo.IDonorsRepository
import com.sslwireless.androidarch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(var moviesRepo: IDonorsRepository) : BaseViewModel() {
    suspend fun getConfigurations() =
        moviesRepo.fetchConfigurations()
}