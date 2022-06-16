package com.sslwireless.androidarch.ui.screens.dashboard.painter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.sslwireless.androidarch.ui.theme.VeryPaleGreen


@Destination
@Composable
fun PainterHomeContent(
    navigator: DestinationsNavigator? = null,
    onItemSelected: (item: HomeGridItem) -> Unit
) {
    val gridItems = listOf(
        HomeGridItem(R.id.scan_qr, "Scan QR", R.drawable.ic_scan_qr),
        HomeGridItem(R.id.history, "History", R.drawable.ic_order_history),
        HomeGridItem(R.id.claim, "Claim", R.drawable.ic_claim),
        HomeGridItem(R.id.program_details, "Program Details", R.drawable.ic_program_details),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 15.dp,
                vertical = 20.dp
            )
            .background(color = MaterialTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(30.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(color = VeryPaleGreen),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Available Point: 2000",
                textAlign = TextAlign.Center,
                style = Typography.caption,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Explore services",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = SlateGrey,
            modifier = Modifier.fillMaxWidth()
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            Modifier.padding(horizontal = 30.dp)
        ) {
            items(gridItems.size) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .clickable {
                            onItemSelected(gridItems[it])
                        }
                ) {
                    Image(
                        painter = painterResource(gridItems[it].imageRes),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .height(50.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = gridItems[it].title,
                        style = Typography.caption,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PainterHomeContentPreview() {
    PainterHomeContent(){}
}