package com.abrsoftware.fletiotestapp.data.remote

import com.abrsoftware.fletiotestapp.data.model.CommentModel
import com.abrsoftware.fletiotestapp.data.model.VehicleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface FleetioTestApi {

    @GET(Routes.GET_VEHICLESLIST)
    suspend fun getListVehicles(
        @Query("page") page: String?,
        @HeaderMap headers: Map<String, String>?
    ): Response<List<VehicleModel>>

    @GET(Routes.GET_COMMENTLIST)
    suspend fun getListComment(
        @Query("id") id: String?,
        @HeaderMap headers: Map<String, String>?
    ): Response<List<CommentModel>>
}