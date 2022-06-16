package com.sslwireless.androidarch.ui.screens.change_password

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
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
fun ChangePasswordScreen(navigator: DestinationsNavigator? = null) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }

    var retypePassword by remember { mutableStateOf(TextFieldValue("")) }
    var retypePasswordVisible by remember { mutableStateOf(false) }

    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    CommonToolbar(title = "CHANGE PASSWORD") {
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
                                value = password,
                                label = { Text(text = "Enter Password") },
                                maxLines = 1,
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                onValueChange = {
                                    password = it
                                },
                                trailingIcon = {
                                    val image = if (passwordVisible)
                                        Icons.Filled.Visibility
                                    else Icons.Filled.VisibilityOff

                                    // Localized description for accessibility services
                                    val description =
                                        if (passwordVisible) "Hide password" else "Show password"

                                    // Toggle button to hide or display password
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        Icon(imageVector = image, description)
                                    }
                                },
                            )
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = retypePassword,
                                label = { Text(text = "Retype Password") },
                                maxLines = 1,
                                visualTransformation = if (retypePasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                onValueChange = {
                                    retypePassword = it
                                },
                                trailingIcon = {
                                    val image = if (retypePasswordVisible)
                                        Icons.Filled.Visibility
                                    else Icons.Filled.VisibilityOff

                                    // Localized description for accessibility services
                                    val description =
                                        if (retypePasswordVisible) "Hide password" else "Show password"

                                    // Toggle button to hide or display password
                                    IconButton(onClick = {
                                        retypePasswordVisible = !retypePasswordVisible
                                    }) {
                                        Icon(imageVector = image, description)
                                    }
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
                                Text("SAVE CHANGES", style = Typography.button)
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
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen()
}