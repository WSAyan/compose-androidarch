package com.sslwireless.androidarch.ui.screens.splash

import com.sslwireless.androidarch.network.NetworkState
import com.sslwireless.androidarch.repo.IMoviesRepository
import com.sslwireless.androidarch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(var moviesRepo: IMoviesRepository) : BaseViewModel() {
    suspend fun getConfigurations() =
        baseApiCall(NetworkState.Data(moviesRepo.fetchConfigurations()))

    suspend fun getConfigurations2() =
        moviesRepo.fetchConfigurations2()
}