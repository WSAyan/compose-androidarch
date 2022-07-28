package com.sslwireless.androidarch.ui.base

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.network.AppNetworkState
import com.sslwireless.androidarch.network.IApiService
import com.sslwireless.androidarch.network.data.resources.ResourcesResponse
import com.sslwireless.androidarch.repo.BaseRepository
import com.sslwireless.androidarch.repo.IBaseRepository
import com.sslwireless.androidarch.repo.donors.IDonorsRepository
import com.sslwireless.androidarch.ui.util.logout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()

    private val _unauthorized = MutableStateFlow(false)
    val unauthorized = _unauthorized.asStateFlow()

    private val _uiState: MutableStateFlow<UIState<Any>?> = MutableStateFlow(null)
    val uiState: StateFlow<UIState<Any>?> = _uiState

    protected fun <T : Any> generateUiState(state: AppNetworkState<T>) {
        when (state) {
            is AppNetworkState.Loading -> {
                _uiState.value = UIState.Loading

                _showProgressBar.value = true

                _unauthorized.value = false
            }
            is AppNetworkState.Data -> {
                _uiState.value = UIState.DataLoaded(state.data)

                _showProgressBar.value = false

                _unauthorized.value = false
            }
            is AppNetworkState.Error -> {
                _uiState.value =
                    UIState.Error(state.exception.errorMessage ?: "", state.unauthorized)

                _showProgressBar.value = false

                _unauthorized.value = state.unauthorized
            }
        }
    }

    fun forceLogout(
        navigator: DestinationsNavigator?,
        baseRepository: IBaseRepository
    ) {
        baseRepository.destroyCache()

        navigator?.logout()
    }
}

sealed class UIState<out T> {
    data class DataLoaded<out T>(val data: T) : UIState<T>()

    object Loading : UIState<Nothing>()

    data class Error(var message: String, var unAuthorized: Boolean = false) :
        UIState<Nothing>()

}