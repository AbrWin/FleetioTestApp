package com.abrsoftware.fletiotestapp.view.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.abrsoftware.fletiotestapp.domain.repository.CommentRepository
import javax.inject.Inject
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.abrsoftware.fletiotestapp.domain.util.Resource
import com.abrsoftware.fletiotestapp.domain.util.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
) : ViewModel() {

    var state: CommentState by mutableStateOf(CommentState())
        private set

    fun loadComments(idVehicle: String) {
        viewModelScope.launch {
            // Validar entrada
            if (idVehicle.isBlank()) {
                state = state.copy(
                    commentList = null,
                    isLoading = false,
                    error = "ID de vehículo inválido"
                )
                return@launch
            }
            
            state = state.copy(
                isLoading = true,
                error = null
            )

            try {
                when (val result = repository.getComments(idVehicle)) {
                    is Resource.Success -> {
                        state = state.copy(
                            commentList = result.data ?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            commentList = null,
                            isLoading = false,
                            error = result.message ?: "Error desconocido al cargar comentarios"
                        )
                    }
                }
            } catch (e: Exception) {
                state = state.copy(
                    commentList = null,
                    isLoading = false,
                    error = ErrorHandler.getErrorMessage(e)
                )
            }
        }
    }
    
    fun clearError() {
        state = state.copy(error = null)
    }
    
    fun retry(idVehicle: String) {
        clearError()
        loadComments(idVehicle)
    }
}