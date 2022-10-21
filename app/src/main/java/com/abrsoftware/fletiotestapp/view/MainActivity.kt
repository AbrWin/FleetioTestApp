package com.abrsoftware.fletiotestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.abrsoftware.fletiotestapp.ui.navigation.Navigation
import com.abrsoftware.fletiotestapp.view.ui.theme.FleetioAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FleetioAppTheme {
                Navigation()
            }
        }
    }
}
