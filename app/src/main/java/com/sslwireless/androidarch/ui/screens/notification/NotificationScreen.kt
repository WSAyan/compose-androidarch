package com.sslwireless.androidarch.ui.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.components.ToolbarWithMenus
import com.sslwireless.androidarch.ui.data.MenuItemData
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography
import com.sslwireless.androidarch.ui.util.showToast

@Destination
@Composable
fun NotificationScreen(
    navigator: DestinationsNavigator? = null
) {
    val context = LocalContext.current

    val notficationItems = listOf<String>(
        /*"notification lorem ipsum lorem ipsum lorem ipsum lorem ipsum 1",
        "notification lorem ipsum lorem ipsum lorem ipsum lorem ipsum 2",
        "notification lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum3",
        "notification lorem ipsum lorem ipsum lorem ipsum lorem ipsum 4",
        "notification lorem ipsum lorem ipsum 5",
        "notification lorem ipsum lorem ipsum 6",
        "notification lorem ipsum lorem ipsum 7",
        "notification lorem ipsum lorem ipsum lorem ipsum lorem ipsum8",
        "notification lorem ipsum lorem ipsum lorem ipsum lorem ipsum9",
        "notification lorem ipsum lorem ipsum 10",
        "notification lorem ipsum lorem ipsum lorem ipsum lorem ipsum11",
        "notification lorem ipsum lorem ipsum 12",*/
    )
    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    ToolbarWithMenus(
                        title = context.getString(R.string.notification),
                        menuList = listOf(
                            MenuItemData(
                                id = R.id.clear_all_menu,
                                label = R.string.clear_all
                            ),
                        ),
                        onBackPressed = {
                            navigator?.navigateUp()
                        },
                        onActionPressed = {
                            when (it.id) {
                                R.id.clear_all_menu -> {

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
                        if (notficationItems.isEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_notificaiton_not_found),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(128.dp)
                                )
                                Spacer(modifier = Modifier.height(18.dp))
                                Text(
                                    text = "No notification found",
                                    style = Typography.h1,
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "We didn't found any notification for you.\n please stay with us.",
                                    style = Typography.caption,
                                    modifier = Modifier.fillMaxWidth(),
                                    color = SlateGrey,
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                items(notficationItems.size) { index ->
                                    NotificationItemContent(notficationItems[index]) {

                                    }
                                    Divider(modifier = Modifier.height(.5.dp))
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
fun NotificationScreenPreview() {
    NotificationScreen()
}