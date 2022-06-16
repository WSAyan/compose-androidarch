package com.sslwireless.androidarch.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.screens.destinations.HistoryScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.NotificationScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.ProfileScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.ScanQRScreenDestination
import com.sslwireless.androidarch.ui.screens.dashboard.dealer.DealerHomeContent
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.Typography


@Destination
@Composable
fun DashboardScreen(navigator: DestinationsNavigator? = null) {
    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                content = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(R.drawable.bg_top_home),
                            contentDescription = "",
                            alignment = Alignment.TopStart,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.logo_1),
                                    contentDescription = "",
                                    modifier = Modifier.size(42.dp)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Box {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_notification),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(22.dp)
                                            .clickable {
                                                navigator?.navigate(direction = NotificationScreenDestination())
                                            },
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.Circle,
                                        tint = Color.Red,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(10.dp)
                                            .align(Alignment.TopEnd)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.ic_baseline_person_24),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(shape = CircleShape)
                                        .border(
                                            width = 1.dp,
                                            shape = CircleShape,
                                            color = MaterialTheme.colors.primary
                                        )
                                        .clickable {
                                            navigator?.navigate(direction = ProfileScreenDestination())
                                        },
                                )
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                            Text(
                                text = "Total Amount",
                                style = Typography.caption,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "৳",
                                    style = Typography.caption,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "3000",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(35.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topStart = 15.dp,
                                            topEnd = 15.dp
                                        )
                                    )
                                    .background(color = MaterialTheme.colors.background)
                            ) {
                                /*PainterHomeContent() {
                                    when (it.id) {
                                        R.id.scan_qr -> {
                                            navigator?.navigate(direction = ScanQRScreenDestination())
                                        }
                                        R.id.history -> {
                                            navigator?.navigate(direction = HistoryScreenDestination())
                                        }
                                        R.id.claim -> {

                                        }
                                        R.id.program_details -> {

                                        }
                                    }
                                }*/
                                DealerHomeContent() {
                                    when (it.id) {
                                        R.id.scan_qr -> {
                                            navigator?.navigate(direction = ScanQRScreenDestination())
                                        }
                                        R.id.history -> {
                                            navigator?.navigate(direction = HistoryScreenDestination())
                                        }
                                        R.id.claim -> {

                                        }
                                        R.id.program_details -> {

                                        }
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
fun DashboardScreenPreview() {
    DashboardScreen()
}