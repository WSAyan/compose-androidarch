package com.wsayan.androidarch.ui.screens.donors

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wsayan.androidarch.R
import com.wsayan.androidarch.network.data.donors.Donor
import com.wsayan.androidarch.ui.theme.SlateGrey

@Composable
fun DonorItemContent(data: Donor? = null, onItemClicked: (item: Donor?) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        SideEffect {

        }
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
            AsyncImage(
                model = "https://ui-avatars.com/api/?name=${data?.name}&background=random",
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.ic_baseline_person_24),
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = data?.name ?: "",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                )
                Text(
                    text = "Age: ${data?.age} years, Gender: ${data?.gender}, Company: ${data?.company_name}",
                    color = SlateGrey,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = data?.blood_group ?: "N/A",
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