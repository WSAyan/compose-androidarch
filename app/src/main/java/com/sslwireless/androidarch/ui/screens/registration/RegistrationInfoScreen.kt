package com.sslwireless.androidarch.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.screens.destinations.ChangePasswordScreenDestination
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.Typography

@Destination
@Composable
fun RegistrationInfoScreen(
    navigator: DestinationsNavigator? = null
) {
    val context = LocalContext.current


    NipponCANTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
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
                            .padding(horizontal = 15.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        RegistrationInfoItem(label = "Name", data = "John Doe")
                        RegistrationInfoItem(label = "Rocket Ac No.", data = "0172574837260")
                        RegistrationInfoItem(label = "Linked Dealer Code", data = "1293")
                        RegistrationInfoItem(label = "Linked Dealer Name", data = "Dealer Name")
                        RegistrationInfoItem(label = "District", data = "Dhaka")
                        RegistrationInfoItem(label = "Division", data = "Dhaka")
                        RegistrationInfoItem(label = "Date of Birth", data = "19 November 1992")
                        RegistrationInfoItem(label = "User Type", data = "Dealer")

                        Spacer(modifier = Modifier.height(35.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = "",
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .size(width = 160.dp, height = 94.dp),
                                contentScale = ContentScale.FillBounds
                            )

                            Spacer(modifier = Modifier.width(11.dp))

                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = "",
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .size(width = 160.dp, height = 94.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }

                        Spacer(modifier = Modifier.height(41.dp))

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary
                            ),
                            onClick = {
                                navigator?.navigate(direction = ChangePasswordScreenDestination())
                            },
                        ) {
                            Text(
                                "SUBMIT NOW",
                                style = Typography.button,
                                color = MaterialTheme.colors.onSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            )
        }
    }
}

@Composable
fun RegistrationInfoItem(label: String, data: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Spacer(modifier = Modifier.height(19.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = label,
                style = Typography.caption,
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                textAlign = TextAlign.Start,
            )
            Text(
                text = data,
                style = Typography.caption,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.height(7.dp))

        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )


    }
}


@Preview
@Composable
fun RegistrationInfoScreenPreview() {
    RegistrationInfoScreen()
}