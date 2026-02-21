package com.abrsoftware.fletiotestapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import com.abrsoftware.fletiotestapp.domain.util.ErrorHandler
import com.abrsoftware.fletiotestapp.pagination.VehicleDataSource
import com.abrsoftware.fletiotestapp.view.util.UiEvent
import com.abrsoftware.fletiotestapp.view.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val repository: VehicleRepository
): ViewModel() {

    // Estado reactivo - StateFlow
    private val _errorState = MutableStateFlow<UiState<String>>(UiState.idle())
    val errorState: StateFlow<UiState<String>> = _errorState.asStateFlow()
    
    // Eventos únicos - SharedFlow
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    val usersPager = Pager(PagingConfig(pageSize = 20)) {
        VehicleDataSource(repository)
    }.flow
        .map { pagingData ->
            pagingData.filter { it.default_image_url != null }
        }
        .catch { exception ->
            val errorMessage = ErrorHandler.getErrorMessage(exception)
            _errorState.value = UiState.error(
                message = errorMessage,
                errorType = ErrorHandler.getErrorType(exception).name
            )
        }
        .cachedIn(viewModelScope)
    
    fun clearError() {
        _errorState.value = UiState.idle()
    }
}