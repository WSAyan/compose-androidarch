package com.sslwireless.androidarch.ui.screens.dashboard.dealer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.data.HomeGridItem
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Destination
@Composable
fun DealerHomeContent(
    navigator: DestinationsNavigator? = null,
    onItemSelected: (item: HomeGridItem) -> Unit
) {
    val listItems = listOf(
        HomeGridItem(R.id.scan_qr, "Scan QR", R.drawable.ic_scan_qr),
        HomeGridItem(R.id.history, "History", R.drawable.ic_order_history),
        HomeGridItem(R.id.claim, "Claim", R.drawable.ic_claim),
        HomeGridItem(R.id.program_details, "Program Details", R.drawable.ic_program_details),
    )

    val claimItems = listOf("data1", "data2", "data3", "data4", "data5")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 15.dp,
                vertical = 20.dp
            )
            .background(color = MaterialTheme.colors.background)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Explore services",
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            color = SlateGrey,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(listItems.size) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            onItemSelected(listItems[it])
                        }
                ) {
                    Image(
                        painter = painterResource(listItems[it].imageRes),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .height(35.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = listItems[it].title,
                        style = Typography.overline,
                        color = SlateGrey,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (it != listItems.lastIndex) {
                    Spacer(modifier = Modifier.width(15.dp))
                    Divider(
                        modifier = Modifier
                            .height(60.dp)
                            .width(.5.dp),
                        color = MaterialTheme.colors.secondary
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Divider()

        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Recent Claim list", style = Typography.overline, color = SlateGrey)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "See all", style = Typography.overline, color = SlateGrey)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider(modifier = Modifier.height(.5.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(claimItems.size) {
                DealerRecentItemContent() {}
                Divider(modifier = Modifier.height(.5.dp))
            }
        }
    }
}


@Preview
@Composable
fun DealerHomeContentPreview() {
    DealerHomeContent() {}
}