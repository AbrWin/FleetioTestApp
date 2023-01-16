package com.abrsoftware.fletiotestapp.view.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.abrsoftware.fletiotestapp.view.extentions.shimmerEffect
import com.abrsoftware.fletiotestapp.view.ui.theme.DarkBlue

@Composable
fun ShimmerListItem() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(10) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .height(200.dp)
                        .padding(start = 12.dp, end = 12.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.BottomStart,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shimmerEffect()
                                .height(40.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

