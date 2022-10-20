package com.abrsoftware.fletiotestapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.abrsoftware.fletiotestapp.view.VehicleItem
import com.abrsoftware.fletiotestapp.view.components.ErrorItem
import com.abrsoftware.fletiotestapp.view.components.LoadingItem
import com.abrsoftware.fletiotestapp.view.screens.VehicleScreenList
import com.abrsoftware.fletiotestapp.view.viewmodel.VehicleListViewModel
import com.abrsoftware.fletiotestapp.view.ui.theme.DarkBlue
import com.abrsoftware.fletiotestapp.view.ui.theme.FleetioAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FleetioAppTheme {
                VehicleScreenList()
            }
        }
    }
}
