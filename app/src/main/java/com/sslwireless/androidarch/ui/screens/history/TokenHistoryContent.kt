package com.sslwireless.androidarch.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.ui.theme.GreyWhite
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Composable
fun TokenHistoryContent(navigator: DestinationsNavigator? = null) {
    val historyViewModel: HistoryViewModel = hiltViewModel()

    NipponCANTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            color = MaterialTheme.colors.background,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Available Balance",
                    style = Typography.caption,
                    color = SlateGrey,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$ 15,000",
                    style = Typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))

                if (historyViewModel.selectedDate.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colors.primary,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .clickable {
                                    historyViewModel.selectedDate = ""
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = historyViewModel.selectedDate,
                                style = Typography.caption,
                                color = MaterialTheme.colors.onPrimary,
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 3.dp, bottom = 3.dp)
                                    .fillMaxHeight()
                                    .align(Alignment.CenterVertically),
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(15.dp)
                                    .fillMaxHeight()
                                    .align(Alignment.CenterVertically),
                                tint = MaterialTheme.colors.onPrimary
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }

                Divider(color = GreyWhite, thickness = 1.dp)

                for (i in 0 until 10) {
                    HistoryItemContent()
                    Divider(color = GreyWhite, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                }
            }
        }

    }
}


@Preview
@Composable
fun TokenHistoryContentPreview() {
    TokenHistoryContent()
}