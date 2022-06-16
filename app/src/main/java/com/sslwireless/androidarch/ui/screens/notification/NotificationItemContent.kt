package com.sslwireless.androidarch.ui.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography


@Composable
fun NotificationItemContent(data: String? = null, onItemClicked: (item: String?) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .background(color = MaterialTheme.colors.background)
            .padding(horizontal = 15.dp, vertical = 18.dp)
            .clickable {
                onItemClicked(data)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_notification_unread),
            contentDescription = "",
            Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = data ?: "create_user_account notification placed \nto be here",
                style = Typography.caption,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                maxLines = 2,
            )
            Text(
                text = "20 Jun, 2020",
                color = SlateGrey,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
            )
        }
    }
}


@Preview
@Composable
fun NotificationItemContentPreview() {
    NotificationItemContent() {}
}