package com.abrsoftware.fletiotestapp.data.repository

import android.content.Context
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.data.model.VehicleModel
import com.abrsoftware.fletiotestapp.data.remote.FleetioTestApi
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle
import com.abrsoftware.fletiotestapp.domain.vehicle.toDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val api: FleetioTestApi,
    @ApplicationContext private val context: Context
) : VehicleRepository {

    override suspend fun getVehicles(pagi: Int): List<Vehicle> {
        return withContext(Dispatchers.IO) {
            val headers = mapOf(
                "Authorization" to context.getString(R.string.token_auth),
                "Account-Token" to context.getString(R.string.token_account)
            )

            val response = api.getListVehicles(pagi.toString(), headers)
            (response.body() as List<VehicleModel>).map { it.toDomain() }
        }
    }
}