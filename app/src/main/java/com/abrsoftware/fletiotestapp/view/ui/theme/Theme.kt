package com.abrsoftware.fletiotestapp.view.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FleetioAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}