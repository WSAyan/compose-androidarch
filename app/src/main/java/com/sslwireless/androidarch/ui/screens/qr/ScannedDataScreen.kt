package com.sslwireless.androidarch.ui.screens.qr

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.screens.destinations.OTPScreenDestination
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.Typography

@Destination
@Composable
fun ScannedDataScreen(navigator: DestinationsNavigator? = null) {
    var uniqueCode by remember { mutableStateOf(TextFieldValue("")) }
    var productSku by remember { mutableStateOf(TextFieldValue("")) }
    var tokenValue by remember { mutableStateOf(TextFieldValue("")) }


    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    CommonToolbar(title = "SCANNED DATA") {
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
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = uniqueCode,
                                label = { Text(text = "Unique Code Auto fill") },
                                maxLines = 1,
                                onValueChange = {
                                    uniqueCode = it
                                },
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = productSku,
                                label = { Text(text = "Product SKU Auto fill") },
                                maxLines = 1,
                                onValueChange = {
                                    productSku = it
                                },
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = tokenValue,
                                label = { Text(text = "Token Value Auto fill") },
                                maxLines = 1,
                                onValueChange = {
                                    tokenValue = it
                                },
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.primary
                                ),
                                onClick = {
                                    navigator?.navigate(OTPScreenDestination())
                                },
                            ) {
                                Text("SUBMIT", style = Typography.button)
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
fun ScannedDataScreenPreview() {
    ScannedDataScreen()
}