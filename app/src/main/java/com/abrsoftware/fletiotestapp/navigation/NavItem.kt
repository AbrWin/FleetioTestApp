package com.abrsoftware.fletiotestapp.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class NavItem(
    internal val baseRoute: String
) {
    object VehicleListNavItem : NavItem("vehicleList")
    object ProfileNavItem : NavItem("vehicleDetail")

    val route = run{

        listOf(baseRoute)

            .joinToString ("/")
    }


}



