package com.abrsoftware.fletiotestapp.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.data.model.CommentModel
import com.abrsoftware.fletiotestapp.data.remote.FleetioTestApi
import com.abrsoftware.fletiotestapp.domain.comment.Comment
import com.abrsoftware.fletiotestapp.domain.comment.toDomain
import com.abrsoftware.fletiotestapp.domain.repository.CommentRepository
import com.abrsoftware.fletiotestapp.domain.util.AppException
import com.abrsoftware.fletiotestapp.domain.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CommentRepositoryImp @Inject constructor(
    private val api: FleetioTestApi,
    @ApplicationContext private val context: Context
) : CommentRepository {
    @SuppressLint("NewApi")
    override suspend fun getComments(idVehicle: String): Resource<List<Comment>> {
        return withContext(Dispatchers.IO) {
            try {
                val headers = mapOf(
                    "Authorization" to context.getString(R.string.token_auth),
                    "Account-Token" to context.getString(R.string.token_account)
                )
                
                val response = api.getListComment(idVehicle, headers)
                
                if (response.isSuccessful && response.body() != null) {
                    val finalResult = (response.body() as List<CommentModel>).map { it.toDomain() }
                    Resource.Success(data = finalResult)
                } else {
                    Resource.Error(
                        message = response.message() ?: "Error en la respuesta del servidor"
                    )
                }
            } catch (e: HttpException) {
                val errorMessage = when (e.code()) {
                    401 -> "No autorizado para ver comentarios"
                    403 -> "Acceso prohibido"
                    404 -> "Comentarios no encontrados"
                    500 -> "Error interno del servidor"
                    else -> e.message ?: "Error HTTP"
                }
                Resource.Error(message = errorMessage)
            } catch (e: IOException) {
                Resource.Error(message = "Error de conexión. Verifica tu internet")
            } catch (e: Exception) {
                Resource.Error(
                    message = e.message ?: "Error inesperado al obtener comentarios"
                )
            }
        }
    }
}