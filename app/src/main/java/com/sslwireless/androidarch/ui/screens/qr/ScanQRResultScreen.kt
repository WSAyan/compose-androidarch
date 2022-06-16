package com.sslwireless.androidarch.ui.screens.qr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.components.LoadLottie
import com.sslwireless.androidarch.ui.theme.*

@Destination
@Composable
fun ScanQRResultScreen(navigator: DestinationsNavigator? = null, isError: Boolean) {
    val context = LocalContext.current


    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 15.dp, vertical = 50.dp)
                            .background(color = MaterialTheme.colors.background)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(160.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            if (isError) {
                                R.raw.anim_error.LoadLottie(repeat = 2)
                            } else {
                                R.raw.anim_success.LoadLottie(repeat = 2)
                            }
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "QR Successful!",
                            style = Typography.body1,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "QR submitted successfully. We are \nadding 30 BDT in your wallet",
                            style = Typography.caption,
                            color = SlateGrey,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(27.dp))
                        if (!isError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Total amount",
                                style = Typography.caption,
                                color = SlateGrey,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "৳",
                                    style = Typography.caption,
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "30",
                                    style = Typography.h1,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        Button(
                            onClick = {
                                navigator?.navigateUp()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = IcedBlue
                            ),
                        ) {
                            Text(
                                text = "GO HOME",
                                color = DuskBlue,
                                style = Typography.button
                            )
                        }

                    }
                },
            )

        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun ScanQRResultScreen() {
    ScanQRScreen()
}