package com.sslwireless.androidarch.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sslwireless.androidarch.network.NetworkErrorExceptions
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.util.showToast
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

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

fun <T : Any> LazyPagingItems<T>.paginationListHandler(context: Context, listScope: LazyListScope) {
    when {
        loadState.refresh is LoadState.Loading -> {
            listScope.item {
                ListProgressBar()
            }
        }
        loadState.append is LoadState.Loading -> {
            listScope.item {
                ListProgressBar()
            }
        }
        loadState.refresh is LoadState.Error -> {
            context.showToast(
                ((loadState.append as LoadState.Error).error as NetworkErrorExceptions).message
                    ?: "Something went wrong!"
            )
        }
        loadState.append is LoadState.Error -> {
            context.showToast(
                ((loadState.append as LoadState.Error).error as NetworkErrorExceptions).message
                    ?: "Something went wrong!"
            )
        }
    }
}

