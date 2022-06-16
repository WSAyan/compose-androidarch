package com.sslwireless.androidarch.ui.screens.phone_number

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.screens.destinations.PasswordScreenDestination
import com.sslwireless.androidarch.ui.theme.GreyWhite
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Destination
@Composable
fun PhoneNumberScreen(navigator: DestinationsNavigator? = null) {
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }

    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(R.drawable.bg_top_1),
                        alignment = Alignment.TopStart,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "content description"
                    )


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 36.dp),
                    ) {
                        Image(
                            modifier = Modifier
                                .size(70.dp),
                            alignment = Alignment.TopStart,
                            painter = painterResource(R.drawable.logo_1),
                            contentDescription = "content description"
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "Get Started",
                        style = Typography.h1,
                        color = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Enter your Rocket number",
                        style = Typography.caption,
                        color = SlateGrey
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = phoneNumber,
                        label = { Text(text = "Enter Rocket Number") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        onValueChange = {
                            phoneNumber = it
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = GreyWhite
                        ),
                        onClick = {
                            //navController?.navigate(PASSWORD_SCREEN)
                            navigator?.navigate(PasswordScreenDestination())
                        },
                    ) {
                        Text("PROCEED", style = Typography.button)
                    }
                }

            }

        }
    }
}

@Preview
@Composable
fun EnterPhoneNumberScreenPreview() {
    PhoneNumberScreen()
}