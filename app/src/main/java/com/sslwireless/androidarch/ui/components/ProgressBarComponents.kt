package com.sslwireless.androidarch.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBarHandler(show: Boolean) {
    if (!show) return

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.Center)
        )

    }
}


@Composable
fun ListProgressBar() {
    CircularProgressIndicator(
        modifier =
        Modifier
            .testTag("ProgressBarItem")
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}