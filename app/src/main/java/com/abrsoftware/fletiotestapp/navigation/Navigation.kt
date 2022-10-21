package com.abrsoftware.fletiotestapp.ui.navigation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle
import com.abrsoftware.fletiotestapp.view.screens.VehicleDetail
import com.abrsoftware.fletiotestapp.view.screens.VehicleScreenList


@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.VehicleListNavItem.route,
    ) {
        composable(NavItem.VehicleListNavItem) {
            VehicleScreenList(onNavigate = { vehicle ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "vehicle",
                    value = vehicle
                )
                navController.navigate(NavItem.ProfileNavItem.route)
            })
        }
        composable(NavItem.ProfileNavItem) {
            val vehicle =
                navController.previousBackStackEntry?.savedStateHandle?.get<Vehicle>("vehicle")
            if(vehicle != null){
                VehicleDetail(vehicle = vehicle)
            }
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(key: String): T {
    val value = arguments?.get(key)
    requireNotNull(value)
    return value as T
}