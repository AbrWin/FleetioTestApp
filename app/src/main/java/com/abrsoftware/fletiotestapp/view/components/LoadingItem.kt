package com.abrsoftware.fletiotestapp.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier
                .width(52.dp)
                .height(52.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )
    }
}