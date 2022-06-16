package com.sslwireless.androidarch.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.components.Tabs
import com.sslwireless.androidarch.ui.components.ToolbarWithMenus
import com.sslwireless.androidarch.ui.data.MenuItemData
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.util.datePicker
import com.sslwireless.androidarch.ui.util.showToast

@Destination
@Composable
fun HistoryScreen(
    navigator: DestinationsNavigator? = null
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = 2)
    val historyViewModel: HistoryViewModel = hiltViewModel()

    context.showToast(historyViewModel.moviesRepo.imageBaseUrl)

    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    ToolbarWithMenus(
                        title = "HISTORY",
                        menuList = listOf(
                            MenuItemData(
                                id = R.id.search_menu,
                                drawable = R.drawable.ic_search,
                                isExpandableSearch = true
                            ),
                            MenuItemData(
                                id = R.id.calendar_menu,
                                drawable = R.drawable.ic_calendar
                            ),
                        ),
                        onBackPressed = {
                            navigator?.navigateUp()
                        },
                        onActionPressed = {
                            when (it.id) {
                                R.id.search_menu -> {

                                }
                                R.id.calendar_menu -> {
                                    context.datePicker(onDatePick = { date ->
                                        historyViewModel.selectedDate = date
                                    }).show()
                                }
                            }
                        },
                        onSearchValueChange = {

                        },
                        onSearchKeyPressed = {
                            context.showToast(it)
                        },
                    )
                },
                content = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Tabs(
                            pagerState = pagerState,
                            list = listOf("Loyalty History", "Token history")
                        )
                        HistoryTabsContent(pagerState = pagerState)
                    }
                }
            )
        }

    }
}

@Composable
fun HistoryTabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> LoyaltyHistoryContent()
            1 -> TokenHistoryContent()
        }
    }
}


@Preview
@Composable
fun HistoryScreenPreview() {
    HistoryScreen()
}