package com.sslwireless.androidarch.ui.screens.donors

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.network.data.donors.Donor
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Composable
fun DonorItemContent(data: Donor? = null, onItemClicked: (item: Donor?) -> Unit) {
    val name by remember { mutableStateOf(data?.name ?: "N/A") }
    val desc by remember { mutableStateOf("Age: ${data?.age} years, Gender: ${data?.gender}, Company: ${data?.company_name}") }
    val bloodGroup by remember { mutableStateOf(data?.blood_group ?: "N/A") }

    Card(modifier = Modifier.fillMaxWidth()) {
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
                painter = painterResource(id = R.drawable.ic_baseline_person_24),
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                )
                Text(
                    text = desc,
                    color = SlateGrey,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = bloodGroup,
                style = MaterialTheme.typography.h1,
                color = Color.Red,
                textAlign = TextAlign.End,
            )
        }
    }

}

@Preview
@Composable
fun DonorItemContentPreview() {
    DonorItemContent() {}
}