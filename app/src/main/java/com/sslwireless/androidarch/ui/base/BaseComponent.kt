package com.sslwireless.androidarch.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BaseComponent(
    backgroundColor: Color = MaterialTheme.colors.background,
    progressBarState: State<Boolean>? = null,
    unauthorizedState: State<Boolean>? = null,
    progressBarContent: @Composable (Boolean) -> Unit,
    unAuthorizedContent: @Composable (Boolean) -> Unit,
    bodyContent: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        bodyContent()

        progressBarContent(progressBarState?.value ?: false)

        unAuthorizedContent(unauthorizedState?.value ?: false)
    }
}