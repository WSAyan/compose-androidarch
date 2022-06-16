package com.sslwireless.androidarch.ui.screens.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.sslwireless.androidarch.repo.IMoviesRepository
import com.sslwireless.androidarch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(var moviesRepo: IMoviesRepository) : BaseViewModel() {
    var selectedDate by mutableStateOf("")
}