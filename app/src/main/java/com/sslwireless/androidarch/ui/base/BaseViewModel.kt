package com.sslwireless.androidarch.ui.base

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.network.AppNetworkState
import com.sslwireless.androidarch.network.IApiService
import com.sslwireless.androidarch.repo.BaseRepository
import com.sslwireless.androidarch.repo.IBaseRepository
import com.sslwireless.androidarch.repo.donors.IDonorsRepository
import com.sslwireless.androidarch.ui.util.logout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    var showProgressBar by mutableStateOf(false)

    private val _uiState: MutableStateFlow<UIState<Any>?> = MutableStateFlow(null)

    val uiState: StateFlow<UIState<Any>?> = _uiState

    protected fun <T : Any> handleUiState(state: AppNetworkState<T>) {
        when (state) {
            is AppNetworkState.Loading -> {
                _uiState.value = UIState.Loading
            }
            is AppNetworkState.Data -> {
                _uiState.value = UIState.DataLoaded(state.data)

            }
            is AppNetworkState.Error -> {
                _uiState.value =
                    UIState.Error(state.exception.errorMessage ?: "", state.unauthorized)
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