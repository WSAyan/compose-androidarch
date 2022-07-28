package com.sslwireless.androidarch.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.sslwireless.androidarch.repo.donors.IDonorsRepository
import com.sslwireless.androidarch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var donorsRepository: IDonorsRepository
) : BaseViewModel() {
    fun getResources() {
        viewModelScope.launch {
            donorsRepository.fetchResources().collect {
                generateUiState(it)
            }
        }
    }
}