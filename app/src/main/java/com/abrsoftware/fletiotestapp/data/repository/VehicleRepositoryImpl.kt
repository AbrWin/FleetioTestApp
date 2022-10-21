package com.abrsoftware.fletiotestapp.data.repository

import android.util.Log
import com.abrsoftware.fletiotestapp.data.model.VehicleModel
import com.abrsoftware.fletiotestapp.data.remote.FleetioTestApi
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle
import com.abrsoftware.fletiotestapp.domain.vehicle.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val api: FleetioTestApi
) : VehicleRepository {

    override suspend fun getVehicles(pagi: Int): List<Vehicle> {
        return withContext(Dispatchers.IO) {
            Log.d("nextPageNumber->: ", pagi.toString())
            val headers = mapOf(
                "Authorization" to "Token token=a3ddc620b35b609682192c167de1b1f3f5100387",
                "Account-Token" to "798819214b"
            )

            val response = api.getListVehicles(pagi.toString(), headers)
            (response.body() as List<VehicleModel>).map { it.toDomain() }
        }
    }
}