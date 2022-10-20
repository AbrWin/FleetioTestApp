package com.abrsoftware.fletiotestapp.domain.repository

import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle

interface VehicleRepository {
    suspend fun getVehicles(pagi: Int): List<Vehicle>
}