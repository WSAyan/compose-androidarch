package com.sslwireless.androidarch.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.screens.destinations.ChangePasswordScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.ProfileUpdateScreenDestination
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator? = null
) {
    val context = LocalContext.current


    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    CommonToolbar(
                        title = "Profile Information",
                        onBackPressed = {
                            navigator?.navigateUp()
                        },
                    )
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_person_24),
                            contentDescription = "",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(shape = CircleShape)
                                .background(color = MaterialTheme.colors.secondary)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "Shohag Parvez",
                            style = Typography.body1,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "08, D block, New Elephant road, \n Dhanmondi, Dhaka",
                            style = Typography.caption,
                            modifier = Modifier.fillMaxWidth(),
                            color = SlateGrey,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "01656556565",
                            style = Typography.caption,
                            modifier = Modifier.fillMaxWidth(),
                            color = SlateGrey,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        Divider(modifier = Modifier.height(1.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable {
                                    navigator?.navigate(direction = ProfileUpdateScreenDestination())
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Profile Edit",
                                style = Typography.caption,
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colors.onBackground,
                                textAlign = TextAlign.Start,
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_user),
                                contentDescription = ""
                            )

                        }

                        Divider(modifier = Modifier.height(1.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable {
                                    navigator?.navigate(direction = ChangePasswordScreenDestination())
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Change Password",
                                style = Typography.caption,
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colors.onBackground,
                                textAlign = TextAlign.Start,
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_lock),
                                contentDescription = ""
                            )

                        }

                        Divider(modifier = Modifier.height(1.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable {

                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Logout",
                                style = Typography.caption,
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colors.onBackground,
                                textAlign = TextAlign.Start,
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_log_out),
                                contentDescription = ""
                            )

                        }

                        Divider(modifier = Modifier.height(1.dp))
                    }

                }
            )
        }

    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}