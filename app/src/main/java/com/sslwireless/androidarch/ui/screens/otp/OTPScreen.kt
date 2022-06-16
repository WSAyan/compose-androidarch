package com.sslwireless.androidarch.ui.screens.otp

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.screens.destinations.DashboardScreenDestination
import com.sslwireless.androidarch.ui.theme.GreyWhite
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Destination
@Composable
fun OTPScreen(navigator: DestinationsNavigator? = null) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }

    var otp: String = ""
    val context = LocalContext.current

    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    CommonToolbar(title = "VERIFICATION") {
                        navigator?.navigateUp()
                    }
                },
                content = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp)
                        ) {
                            Text(
                                text = "Enter OTP",
                                style = Typography.body1,
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Please enter OTP sent to +880 1672574915",
                                style = Typography.caption,
                                color = SlateGrey
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OTPTextFields(length = 4, onFilled = {
                                    otp = it
                                })
                            }
                            Spacer(modifier = Modifier.height(22.dp))
                            Text(
                                "RESEND OTP",
                                style = Typography.caption,
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = GreyWhite
                                ),
                                onClick = {
                                    navigator?.navigate(DashboardScreenDestination())
                                },
                            ) {
                                Text("VERIFY OTP", style = Typography.button)
                            }
                        }
                    }
                },
            )

        }

    }
}

@Preview
@Composable
fun OTPScreenPreview() {
    OTPScreen()
}