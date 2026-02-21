package com.abrsoftware.fletiotestapp.view.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import com.abrsoftware.fletiotestapp.domain.util.ErrorHandler
import com.abrsoftware.fletiotestapp.pagination.VehicleDataSource
import com.abrsoftware.fletiotestapp.view.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val repository: VehicleRepository
): ViewModel() {

    var errorState: UiState<String> by mutableStateOf(UiState.idle())
        private set

    val usersPager = Pager(PagingConfig(pageSize = 20)) {
        VehicleDataSource(repository)
    }.flow
        .map { pagingData ->
            pagingData.filter { it.default_image_url != null }
        }
        .catch { exception ->
            errorState = UiState.error(
                message = ErrorHandler.getErrorMessage(exception),
                errorType = ErrorHandler.getErrorType(exception).name
            )
        }
        .cachedIn(viewModelScope)
    
    fun clearError() {
        errorState = UiState.idle()
    }
}