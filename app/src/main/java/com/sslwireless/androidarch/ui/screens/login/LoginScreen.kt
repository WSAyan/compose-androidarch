package com.sslwireless.androidarch.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.screens.destinations.PhoneNumberScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.RegistrationScreenDestination
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Destination
@Composable
fun LoginScreen(navigator: DestinationsNavigator? = null) {
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }

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
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    Text(
                        text = "Get Started",
                        style = Typography.h1,
                        color = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Please enter user id and password",
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

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password,
                        label = { Text(text = "Password") },
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
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Forgot password?",
                        style = Typography.caption,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        textDecoration = TextDecoration.Underline,
                    )
                    Spacer(modifier = Modifier.height(23.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        onClick = {
                            navigator?.navigate(RegistrationScreenDestination())
                        },
                    ) {
                        Text("LOGIN", style = Typography.button)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = buildAnnotatedString {
                            append("Don't have account yet?")

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                            ) {
                                append(" Get registered!")
                            }
                        },
                        style = Typography.caption,
                        color = SlateGrey,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navigator?.navigate(direction = PhoneNumberScreenDestination())
                            },
                        textAlign = TextAlign.Center,
                    )
                }

            }

        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}