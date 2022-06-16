package com.sslwireless.androidarch.ui.screens.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Composable
fun HistoryItemContent(navigator: DestinationsNavigator? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(color = MaterialTheme.colors.background)
            .padding(horizontal = 15.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_payback),
            contentDescription = "",
            Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Serial No. 01",
                style = Typography.caption,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
            )
            Text(
                text = "Payback : 20 Jun, 2020",
                color = SlateGrey,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "15000",
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.End,
            fontSize = 18.sp
        )
    }
}


@Preview
@Composable
fun HistoryItemContentPreview() {
    HistoryItemContent()
}