package com.abrsoftware.fletiotestapp.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle
import com.abrsoftware.fletiotestapp.view.ui.Thumb
import com.abrsoftware.fletiotestapp.R

@Composable
fun VehicleItem(
    vehicle: Vehicle,
    textColor: Color = Color.White,
    onNavigate: (account: Vehicle) -> Unit
) {
    val imgPath =
        if (vehicle.default_image_url == null) stringResource(R.string.img_fake) else vehicle.default_image_url
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .height(200.dp)
            .padding(start = 12.dp, end = 12.dp)
            .clickable {
                onNavigate(vehicle)
            },
        shape = RoundedCornerShape(16.dp),
    ) {
        Thumb(pathImg = imgPath)
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomStart,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue.copy(0.6f))
                    .height(40.dp)
            )
            Text(
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 10.dp, top = 8.dp),
                textAlign = TextAlign.Start,
                text = "Vehicle model: ${vehicle.id}",
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}