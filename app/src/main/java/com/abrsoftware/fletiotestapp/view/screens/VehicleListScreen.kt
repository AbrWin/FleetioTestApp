package com.abrsoftware.fletiotestapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.abrsoftware.fletiotestapp.view.VehicleItem
import com.abrsoftware.fletiotestapp.view.components.ErrorItem
import com.abrsoftware.fletiotestapp.view.components.LoadingItem
import com.abrsoftware.fletiotestapp.view.ui.theme.DarkBlue
import com.abrsoftware.fletiotestapp.view.viewmodel.VehicleListViewModel

@Composable
fun VehicleScreenList(
    viewModel: VehicleListViewModel = hiltViewModel()
){
    val vehicleList = viewModel.usersPager.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
        ) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = "Vehicle List",
                fontSize = 28.sp,
                color = Color.White
            )
            LazyColumn {
                items(vehicleList) { vehicleListData ->
                    vehicleListData?.let { vehicle->
                        VehicleItem(
                            vehicle = vehicle
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                }
                when (vehicleList.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    is LoadState.Error -> {
                        item {
                            ErrorItem(message = (vehicleList.loadState.append as LoadState.Error).error.message.toString())
                        }
                    }
                }
            }
        }
    }
}