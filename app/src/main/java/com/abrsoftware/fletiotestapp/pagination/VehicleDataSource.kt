package com.abrsoftware.fletiotestapp.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import com.abrsoftware.fletiotestapp.domain.util.AppException
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle
import retrofit2.HttpException
import java.io.IOException

class VehicleDataSource(
    private val repo: VehicleRepository
) : PagingSource<Int, Vehicle>() {

    override fun getRefreshKey(state: PagingState<Int, Vehicle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vehicle> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repo.getVehicles(nextPageNumber)
            
            if (response.isEmpty()) {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            } else {
                LoadResult.Page(
                    data = response,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = nextPageNumber + 1
                )
            }
        } catch (e: IOException) {
            LoadResult.Error(
                AppException.NetworkException("Verifica tu conexión a internet")
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                AppException.ApiException(
                    code = e.code(),
                    errorMessage = e.message ?: "Error en la solicitud HTTP"
                )
            )
        } catch (e: Exception) {
            LoadResult.Error(
                AppException.UnknownException(
                    errorMessage = e.message ?: "Error desconocido al cargar vehículos"
                )
            )
        }
    }
}