package com.wsayan.androidarch.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.wsayan.androidarch.repo.donors.IDonorsRepository
import com.wsayan.androidarch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var donorsRepository: IDonorsRepository
) : BaseViewModel() {
    fun getResources(stateId: Int) {
        viewModelScope.launch {
            donorsRepository.fetchResources().collect {
                generateUiState(stateId, it)
            }
        }
    }
}