package com.abrsoftware.fletiotestapp.data.repository

import android.content.Context
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.data.model.VehicleModel
import com.abrsoftware.fletiotestapp.data.remote.FleetioTestApi
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import com.abrsoftware.fletiotestapp.domain.util.AppException
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle
import com.abrsoftware.fletiotestapp.domain.vehicle.toDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val api: FleetioTestApi,
    @ApplicationContext private val context: Context
) : VehicleRepository {

    override suspend fun getVehicles(pagi: Int): List<Vehicle> {
        return withContext(Dispatchers.IO) {
            try {
                val headers = mapOf(
                    "Authorization" to context.getString(R.string.token_auth),
                    "Account-Token" to context.getString(R.string.token_account)
                )

                val response = api.getListVehicles(pagi.toString(), headers)
                
                if (response.isSuccessful && response.body() != null) {
                    (response.body() as List<VehicleModel>).map { it.toDomain() }
                } else {
                    throw AppException.ApiException(
                        code = response.code(),
                        errorMessage = response.message() ?: "Error en la respuesta del servidor"
                    )
                }
            } catch (e: HttpException) {
                throw AppException.ApiException(
                    code = e.code(),
                    errorMessage = when (e.code()) {
                        401 -> "No autorizado. Verifica tus credenciales"
                        403 -> "Acceso prohibido a los vehículos"
                        404 -> "Recurso no encontrado"
                        500 -> "Error interno del servidor"
                        else -> e.message ?: "Error HTTP desconocido"
                    }
                )
            } catch (e: IOException) {
                throw AppException.NetworkException("Error de conexión al servidor")
            } catch (e: Exception) {
                throw AppException.UnknownException(
                    errorMessage = e.message ?: "Error inesperado al obtener vehículos"
                )
            }
        }
    }
}