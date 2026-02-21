package com.abrsoftware.fletiotestapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle
import com.abrsoftware.fletiotestapp.view.components.BubbleText
import com.abrsoftware.fletiotestapp.view.ui.theme.DarkBlue
import com.abrsoftware.fletiotestapp.view.ui.theme.PersonalStyle
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.abrsoftware.fletiotestapp.view.components.CircularProgress
import com.abrsoftware.fletiotestapp.view.ui.theme.BlueF
import com.abrsoftware.fletiotestapp.view.viewmodel.CommentViewModel

@Composable
fun VehicleDetail(
    vehicle: Vehicle,
    viewModel: CommentViewModel = hiltViewModel()
) {
    val pinsVehicle = listOf(
        stringResource(R.string.id_vehicle).plus(" ") + vehicle.vehicle_status_id.toString(),
        stringResource(R.string.name_type).plus(" ") + vehicle.vehicle_type_name.toString(),
        stringResource(R.string.color_car).plus(" ") + vehicle.vehicle_status_color.toString(),
        stringResource(R.string.fuel_volume_units).plus(" ") + vehicle.fuel_volume_units.toString(),
        stringResource(R.string.ownership).plus(" ") + vehicle.ownership.toString()
    )
    var isButtonVisible by remember { mutableStateOf(true) }

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
                    .data(vehicle.default_image_url)
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
                    items(pinsVehicle) { value ->
                        if (!value.contains("null")) {
                            BubbleText(title = value)
                        }
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
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .background(MaterialTheme.colors.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.title_comments),
                        style = PersonalStyle(Color.White).copy(fontSize = 27.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (isButtonVisible) {
                    Spacer(
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                    )
                    ExtendedFloatingActionButton(
                        modifier = Modifier,
                        onClick = {
                            isButtonVisible = false
                            viewModel.loadComments(vehicle.id.toString())
                        },
                        icon = {
                            Icon(
                                Icons.Filled.Face,
                                contentDescription = "Favorite"
                            )
                        },
                        text = {
                            Text(
                                stringResource(id = R.string.load_comments),
                                style = PersonalStyle(Color.Black).copy(fontSize = 16.sp)
                            )
                        }
                    )
                }else{
                    if(viewModel.state.isLoading) {
                        CircularProgress(BlueF)
                    }
                    
                    viewModel.state.error?.let { error ->
                        com.abrsoftware.fletiotestapp.view.components.ErrorItem(
                            message = error,
                            onDismiss = {
                                viewModel.clearError()
                                isButtonVisible = true
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    
                    if(viewModel.state.commentList != null && viewModel.state.commentList!!.size > 0){
                        LazyColumn(
                            modifier = Modifier
                                .padding(10.dp)
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            content = {
                                items(viewModel.state.commentList!!) { comment ->
                                    CommentItem(comment)
                                }
                            })
                    }
                }
            }
        }
    }
}