package com.abrsoftware.fletiotestapp.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abrsoftware.fletiotestapp.R

@Composable
fun Thumb(pathImg: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .height(160.dp)
                .background(Color.White),
            model = ImageRequest.Builder(LocalContext.current)
                .data(pathImg)
                .crossfade(100)
                .build(),
            placeholder = painterResource(R.drawable.car_placeholder),
            contentDescription = "baseImage",
            contentScale = ContentScale.Crop
        )
    }
}
