package com.sslwireless.androidarch.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.sslwireless.androidarch.ui.theme.SlateGrey
import kotlinx.coroutines.launch

@Composable
fun Tabs(pagerState: PagerState, list: List<String>) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = MaterialTheme.colors.primary
            )
        },
    ) {
        list.forEachIndexed { index, data ->
            Tab(
                text = {
                    Text(
                        data,
                        color = if (pagerState.currentPage == index) MaterialTheme.colors.primary else SlateGrey
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}