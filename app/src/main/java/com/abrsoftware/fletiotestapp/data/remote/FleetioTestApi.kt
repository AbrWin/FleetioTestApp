package com.abrsoftware.fletiotestapp.data.remote

import com.abrsoftware.fletiotestapp.data.model.VehicleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface FleetioTestApi {

    @GET("api/v1/vehicles?")
    suspend fun getListVehicles(
        @Query("page") page: String?,
        @HeaderMap headers: Map<String, String>?
    ): Response<List<VehicleModel>>
}