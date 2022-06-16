package com.sslwireless.androidarch.ui.screens.dashboard.dealer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
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
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Composable
fun DealerRecentItemContent(data: String? = null, onItemClicked: (item: String?) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(color = MaterialTheme.colors.background)
            .padding(vertical = 9.dp)
            .clickable {
                onItemClicked(data)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "John Doe",
                style = Typography.caption,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
            )
            Text(
                text = "0166488484",
                color = SlateGrey,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Date: 12 dec, 2022",
                color = SlateGrey,
                textAlign = TextAlign.End,
                style = Typography.overline,
            )
            Text(
                text = "15000",
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.End,
                style = Typography.caption,
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "",
            tint = SlateGrey,
        )

    }
}


@Preview
@Composable
fun DealerRecentItemContentPreview() {
    DealerRecentItemContent(){}
}