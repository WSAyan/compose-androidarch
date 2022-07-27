package com.sslwireless.androidarch.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.sslwireless.androidarch.ui.theme.SlateGrey

@Composable
fun DottedRectangle(
    radius: Float = 10f,
    dotWidth: Float = 2f,
    bgColor: Color = MaterialTheme.colors.secondary,
    dotColor: Color = SlateGrey
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = bgColor,
                shape = RoundedCornerShape(radius.dp)
            )
    ) {
        drawRoundRect(
            color = dotColor,
            size = size,
            style = Stroke(
                width = dotWidth,
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(
                        10f,
                        10f
                    ), 0f
                )
            ),
            cornerRadius = CornerRadius(radius.dp.toPx()),
        )
    }
}

@Composable
fun AppBackground(
    backgroundColor: Color = MaterialTheme.colors.background,
    progressBarContent: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        bodyContent()

        progressBarContent()
    }
}