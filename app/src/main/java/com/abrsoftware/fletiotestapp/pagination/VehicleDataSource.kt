package com.abrsoftware.fletiotestapp.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import com.abrsoftware.fletiotestapp.domain.vehicle.Vehicle

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
            LoadResult.Page(
                data = response.filter { it.default_image_url != null },
                prevKey = null,
                nextKey = if (response.isNotEmpty()) nextPageNumber + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}