package com.abrsoftware.fletiotestapp.view.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abrsoftware.fletiotestapp.view.ui.theme.*

@Composable
fun BubbleText(title: String) {
    Box(
        modifier = Modifier
            .padding()
            .fillMaxWidth()
            .height(45.dp)
            .padding(start = 8.dp, end = 8.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    )
    {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp),
        ) {
            Box(
                modifier = Modifier.background(Color.White).padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = PersonalStyle(Color.Blue.copy(0.6f)).copy(fontSize = 16.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun PreDialog() {
    BubbleText("Vehicle")
}