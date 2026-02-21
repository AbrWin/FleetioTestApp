package com.abrsoftware.fletiotestapp.view.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abrsoftware.fletiotestapp.view.ui.theme.DarkBlue

/**
 * Refresh button con rotación animada durante la carga
 */
@Composable
fun AnimatedRefreshButton(
    isLoading: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (isLoading) 360f else 0f,
        label = "refresh_rotation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(DarkBlue),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            IconButton(
                onClick = { if (!isLoading) onRefresh() },
                modifier = Modifier
                    .rotate(rotation)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    tint = Color.White
                )
            }
        }
    }
}
