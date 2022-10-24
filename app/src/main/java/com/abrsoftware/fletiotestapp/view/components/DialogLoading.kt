package com.abrsoftware.fletiotestapp.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abrsoftware.fletiotestapp.view.ui.theme.DarkBlue

@Composable
fun DialogLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue),
        contentAlignment = Alignment.Center
    )
    {
        Log.d("DIALOG","DIALOG")
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .align(Alignment.Center),
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(modifier = Modifier.padding(12.dp),
            contentAlignment = Alignment.Center){
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Preview
@Composable
fun PreDialog() {
    DialogLoading()
}