package com.abrsoftware.fletiotestapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle
import com.abrsoftware.fletiotestapp.view.VehicleItem
import com.abrsoftware.fletiotestapp.view.components.*
import com.abrsoftware.fletiotestapp.view.ui.theme.DarkBlue
import com.abrsoftware.fletiotestapp.view.viewmodel.VehicleListViewModel

@Composable
fun VehicleScreenList(
    viewModel: VehicleListViewModel = hiltViewModel(),
    onNavigate: (account: Vehicle) -> Unit
) {
    val vehicleList = viewModel.usersPager.collectAsLazyPagingItems()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            ToolBar()
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .background(DarkBlue)
            )
            if (vehicleList.itemCount == 0) {
                ShimmerListItem()
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlue)
            ) {
                LazyColumn {
                    items(vehicleList) { vehicleListData ->
                        vehicleListData?.let { vehicle ->
                            VehicleItem(
                                vehicle = vehicle,
                                onNavigate = onNavigate
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
}