package com.abrsoftware.fletiotestapp.view.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.view.ui.theme.GreenLight
import com.abrsoftware.fletiotestapp.view.ui.theme.PersonalStyle

@Composable
fun ToolBar() {
    Box(
        modifier = Modifier
            .padding()
            .fillMaxWidth()
            .height(55.dp)
            .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = stringResource(R.string.title_listvehicle),
            style = PersonalStyle(Color.White).copy(fontSize = 27.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}