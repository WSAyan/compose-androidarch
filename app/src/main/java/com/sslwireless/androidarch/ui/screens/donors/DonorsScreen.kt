package com.sslwireless.androidarch.ui.screens.donors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.network.data.donors.Donor
import com.sslwireless.androidarch.ui.base.BaseComponent
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.components.ProgressBarHandler
import com.sslwireless.androidarch.ui.components.paginationListHandler
import com.sslwireless.androidarch.ui.data.NavigationHeader
import com.sslwireless.androidarch.ui.util.open


@Destination
@Composable
fun DonorsScreen(
    navigator: DestinationsNavigator? = null
) {
    val context = LocalContext.current

    val viewModel: DonorsViewModel = hiltViewModel()

    val listState = rememberLazyListState()

    BaseComponent(
        backgroundColor = MaterialTheme.colors.background,
        progressBarState = viewModel.showProgressBar.collectAsState(),
        unauthorizedState = viewModel.unauthorized.collectAsState(),
        progressBarContent = {
            ProgressBarHandler(show = it)
        },
        unAuthorizedContent = {

        }
    ) {
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
                    val donors: LazyPagingItems<Donor> =
                        viewModel.getDonors("").collectAsLazyPagingItems()

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = listState
                        ) {
                            items(items = donors, key = { donor -> donor.id ?: -1 }) { donor ->
                                DonorItemContent(data = donor, onItemClicked = {})
                            }
                            donors.apply {
                                this.paginationListHandler(
                                    context = context,
                                    listScope = this@LazyColumn
                                )
                            }
                        }
                    }
                },
                floatingActionButton = {
                    IconButton(
                        onClick = {
                            navigator?.open(
                                route = R.id.camera,
                                data = NavigationHeader()
                            )
                        },
                        modifier = Modifier.background(
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Camera,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onPrimary
                        )
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