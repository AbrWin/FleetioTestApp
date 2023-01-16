package com.abrsoftware.fletiotestapp.view.screens

import android.R.color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.domain.comment.Comment
import com.abrsoftware.fletiotestapp.view.ui.theme.PersonalStyle


@Composable
fun CommentItem(comment: Comment) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(
                2.dp, color = Color.Blue.copy(0.6f),
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(comment.user_image_url.toString())
                    .crossfade(100)
                    .build(),
                placeholder = painterResource(R.drawable.user_icon),
                contentDescription = "baseImage",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(comment.user_full_name.toString(), style = PersonalStyle(Color.Black).copy(fontSize = 15.sp))
                Spacer(modifier = Modifier.height(2.dp))
                Text(comment.comment.toString())
                Spacer(modifier = Modifier.height(2.dp))
                Text(comment.created_at.toString(), color = Color.Gray,fontSize = 12.sp)
            }
        }

    }
}
