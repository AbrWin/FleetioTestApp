package com.abrsoftware.fletiotestapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.view.components.BubbleText
import com.abrsoftware.fletiotestapp.view.ui.Thumb
import com.abrsoftware.fletiotestapp.view.ui.theme.DarkBlue
import com.abrsoftware.fletiotestapp.view.ui.theme.PersonalStyle

@Composable
fun VehicleDetail() {
    val names = listOf(
        "Abraham",
        "Sanchez",
        "Juarez",
        "Abraham",
        "Sanchez",
        "Juarez",
        "Abraham",
        "Sanchez",
        "Juarez",
        "Abraham",
        "Sanchez",
        "Juarez",
        "Abraham",
        "Sanchez",
        "Juarez",
        "Abraham",
        "Sanchez",
        "Juarez",
        "Abraham",
        "Sanchez",
        "Juarez",
        "Abraham",
        "Sanchez",
        "Juarez",
        "Abraham",
        "Sanchez",
        "Juarez"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.White),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://d8g9nhlfs6lwh.cloudfront.net/api/file/rgaLfSDRguojPXU8Wl3w?signature=bb03b55b1aadb3a7488d080a9f1052dc02dd431b0eb2d6d171137313e1ee7001&policy=eyJoYW5kbGUiOiJyZ2FMZlNEUmd1b2pQWFU4V2wzdyIsImV4cGlyeSI6NDUzNDk0OTM0MywiY2FsbCI6WyJyZWFkIl19")
                    .crossfade(100)
                    .build(),
                placeholder = painterResource(R.drawable.car_placeholder),
                contentDescription = "baseImage",
                contentScale = ContentScale.Crop
            )

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                content = {
                    items(names) { weatherData ->
                        BubbleText(title = weatherData)
                    }
                })
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(35.dp, 35.dp, 0.dp, 0.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .background(Color.Blue.copy(0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.title_comments),
                        style = PersonalStyle(Color.White).copy(fontSize = 27.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PrevVehicleDetail() {
    VehicleDetail()
}