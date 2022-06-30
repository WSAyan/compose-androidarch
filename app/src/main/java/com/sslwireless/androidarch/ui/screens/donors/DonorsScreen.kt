package com.sslwireless.androidarch.ui.screens.donors

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.network.NetworkErrorExceptions
import com.sslwireless.androidarch.network.data.donors.Donor
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.components.ListProgressBar
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.util.showToast


@Destination
@Composable
fun DonorsScreen(
    navigator: DestinationsNavigator? = null
) {
    val context = LocalContext.current

    val viewModel: DonorsViewModel = hiltViewModel()


    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    CommonToolbar(
                        title = "Donors",
                        onBackPressed = {
                            navigator?.navigateUp()
                        },
                    )
                },
                content = {
                    val donorsItem: LazyPagingItems<Donor> =
                        viewModel.getDonors("").collectAsLazyPagingItems()

                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(donorsItem.itemCount) { index ->
                                donorsItem[index]?.let {
                                    DonorItemContent(data = it, onItemClicked = {})
                                }
                            }
                            donorsItem.apply {
                                when {
                                    loadState.refresh is LoadState.Loading -> {
                                        item {
                                            ListProgressBar()
                                        }
                                    }
                                    loadState.append is LoadState.Loading -> {
                                        item {
                                            ListProgressBar()
                                        }
                                    }
                                    loadState.refresh is LoadState.Error -> {
                                        context.showToast(
                                            ((loadState.append as LoadState.Error).error as NetworkErrorExceptions).errorMessage
                                                ?: "Something went wrong!"
                                        )
                                    }
                                    loadState.append is LoadState.Error -> {
                                        context.showToast(
                                            ((loadState.append as LoadState.Error).error as NetworkErrorExceptions).errorMessage
                                                ?: "Something went wrong!"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }

    }
}


@Preview
@Composable
fun DonorsScreenPreview() {
    DonorsScreen()
}