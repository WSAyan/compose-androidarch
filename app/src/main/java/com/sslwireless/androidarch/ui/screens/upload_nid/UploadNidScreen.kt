package com.sslwireless.androidarch.ui.screens.upload_nid

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.components.DottedRectangle
import com.sslwireless.androidarch.ui.data.ImageBody
import com.sslwireless.androidarch.ui.data.NavigationHeader
import com.sslwireless.androidarch.ui.screens.destinations.CameraScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.RegistrationInfoScreenDestination
import com.sslwireless.androidarch.ui.theme.BlueGreen
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Destination
@Composable
fun UploadNidScreen(
    navigator: DestinationsNavigator? = null,
    resultRecipient: ResultRecipient<CameraScreenDestination, ImageBody>? = null
) {
    val context = LocalContext.current

    val instructions =
        listOf("Good lighting", "Capture straight", "Follow camera frame", "Enough readability")

    var nIdFrontUri: Uri? by rememberSaveable { mutableStateOf(null) }
    var nIdBackUri: Uri? by rememberSaveable { mutableStateOf(null) }

    resultRecipient?.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {
                // `GoToProfileConfirmationDestination` was shown but it was canceled
                // and no value was set (example: dialog/bottom sheet dismissed)
            }
            is NavResult.Value -> {
                when (result.value.id) {
                    R.id.capture_nid_front -> {
                        nIdFrontUri = result.value.imageUri
                    }
                    R.id.capture_nid_back -> {
                        nIdBackUri = result.value.imageUri
                    }
                }


            }
        }
    }

    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    CommonToolbar(title = "UPLOAD NID") {
                        navigator?.navigateUp()
                    }
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp)
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clickable {
                                        navigator?.navigate(
                                            direction = CameraScreenDestination(
                                                NavigationHeader(
                                                    id = R.id.capture_nid_front,
                                                    title = "Capture front of NID",
                                                    subtitle = "Place your NID in frame",
                                                )
                                            ),
                                        )
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                if (nIdFrontUri != null) {
                                    AsyncImage(
                                        model = nIdFrontUri,
                                        contentDescription = "",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    DottedRectangle()

                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .size(width = 150.dp, height = 90.dp),
                                            painter = painterResource(R.drawable.capture_nid),
                                            contentDescription = "content description"
                                        )
                                        Spacer(Modifier.height(10.dp))
                                        Text(
                                            textAlign = TextAlign.Center,
                                            text = "Now Capture you NID Front side &\n Upload photo",
                                            style = Typography.caption,
                                            color = SlateGrey,
                                        )
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clickable {
                                        navigator?.navigate(
                                            direction = CameraScreenDestination(
                                                NavigationHeader(
                                                    id = R.id.capture_nid_back,
                                                    title = "Capture back of NID",
                                                    subtitle = "Place your NID in frame",
                                                )
                                            ),
                                        )
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                if (nIdBackUri != null) {
                                    AsyncImage(
                                        model = nIdBackUri,
                                        contentDescription = "",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    DottedRectangle()
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .size(width = 150.dp, height = 90.dp),
                                            painter = painterResource(R.drawable.capture_nid),
                                            contentDescription = "content description"
                                        )
                                        Spacer(Modifier.height(10.dp))
                                        Text(
                                            textAlign = TextAlign.Center,
                                            text = "Now Capture you NID Back side &\n Upload photo",
                                            style = Typography.caption,
                                            color = SlateGrey,
                                        )
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(28.dp))
                            Text(
                                textAlign = TextAlign.Start,
                                text = "Follow Instructions",
                                style = Typography.caption,
                                color = SlateGrey,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val half = instructions.size / 2
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    for (i in 0 until half) {
                                        Row(
                                            Modifier
                                                .weight(1f)
                                                .padding(vertical = 4.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .background(
                                                        shape = CircleShape,
                                                        color = BlueGreen
                                                    ),
                                                contentAlignment = Alignment.Center,
                                            ) {
                                                Text(
                                                    text = "${i + 1}",
                                                    textAlign = TextAlign.Center,
                                                    style = Typography.overline,
                                                    color = Color.White
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = instructions[i],
                                                textAlign = TextAlign.Start,
                                                style = Typography.caption,
                                                color = SlateGrey
                                            )
                                        }
                                    }
                                }

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    for (i in half until instructions.size) {
                                        Row(
                                            Modifier
                                                .weight(1f)
                                                .padding(vertical = 4.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .background(
                                                        shape = CircleShape,
                                                        color = BlueGreen
                                                    ),
                                                contentAlignment = Alignment.Center,
                                            ) {
                                                Text(
                                                    text = "${i + 1}",
                                                    textAlign = TextAlign.Center,
                                                    style = Typography.overline,
                                                    color = Color.White
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = instructions[i],
                                                textAlign = TextAlign.Start,
                                                style = Typography.caption,
                                                color = SlateGrey
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(25.dp))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.primary
                                ),
                                onClick = {
                                    navigator?.navigate(direction = RegistrationInfoScreenDestination())
                                },
                            ) {
                                Text("UPLOAD NID", style = Typography.button)
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
fun UploadNidScreenPreview() {
    UploadNidScreen()
}