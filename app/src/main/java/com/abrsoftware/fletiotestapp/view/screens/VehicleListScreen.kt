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
    val errorState = viewModel.errorState
    
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
            
            // Mostrar error general del ViewModel
            if (errorState.error != null) {
                ErrorItem(
                    message = errorState.error.message,
                    onDismiss = { viewModel.clearError() }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            
            if (vehicleList.itemCount == 0 && vehicleList.loadState.refresh !is LoadState.Loading) {
                ShimmerListItem()
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlue)
            ) {
                LazyColumn {
                    items(vehicleList.itemCount) { index ->
                        vehicleList[index]?.let { vehicle ->
                            VehicleItem(
                                vehicle = vehicle,
                                onNavigate = onNavigate
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                        }
                    }
                    when (vehicleList.loadState.append) {
                        is LoadState.NotLoading -> Unit
                        LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                        is LoadState.Error -> {
                            item {
                                ErrorItem(
                                    message = (vehicleList.loadState.append as LoadState.Error).error.message.toString(),
                                    onDismiss = { vehicleList.retry() }
                                )
                            }
                        }
                    }
                    
                    // Error al hacer refresh
                    when (vehicleList.loadState.refresh) {
                        is LoadState.Error -> {
                            item {
                                ErrorItem(
                                    message = (vehicleList.loadState.refresh as LoadState.Error).error.message.toString(),
                                    onDismiss = { vehicleList.retry() }
                                )
                            }
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}