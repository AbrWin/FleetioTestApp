package com.abrsoftware.fletiotestapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrsoftware.fletiotestapp.domain.repository.CommentRepository
import com.abrsoftware.fletiotestapp.domain.util.Resource
import com.abrsoftware.fletiotestapp.domain.util.ErrorHandler
import com.abrsoftware.fletiotestapp.domain.validation.InputValidators
import com.abrsoftware.fletiotestapp.domain.validation.ValidationResult
import com.abrsoftware.fletiotestapp.view.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
) : ViewModel() {

    // Estado reactivo - StateFlow
    private val _state = MutableStateFlow(CommentState())
    val state: StateFlow<CommentState> = _state.asStateFlow()
    
    // Eventos únicos - SharedFlow
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    fun loadComments(idVehicle: String) {
        // Validar entrada usando validador centralizado
        val validationResult = InputValidators.validateVehicleId(idVehicle)
        if (validationResult !is ValidationResult.Valid) {
            val errorMessage = (validationResult as ValidationResult.Invalid).message
            _state.value = _state.value.copy(
                commentList = null,
                isLoading = false,
                error = errorMessage
            )
            return
        }
        
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )

            try {
                when (val result = repository.getComments(idVehicle)) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            commentList = result.data ?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                        _uiEvent.emit(UiEvent.ShowSuccess("Comentarios cargados exitosamente"))
                    }
                    is Resource.Error -> {
                        val errorMessage = result.message ?: "Error desconocido al cargar comentarios"
                        _state.value = _state.value.copy(
                            commentList = null,
                            isLoading = false,
                            error = errorMessage
                        )
                        _uiEvent.emit(UiEvent.ShowError(errorMessage))
                    }
                }
            } catch (e: Exception) {
                val errorMessage = ErrorHandler.getErrorMessage(e)
                _state.value = _state.value.copy(
                    commentList = null,
                    isLoading = false,
                    error = errorMessage
                )
                _uiEvent.emit(UiEvent.ShowError(errorMessage))
            }
        }
    }
    
    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
    
    fun retry(idVehicle: String) {
        clearError()
        loadComments(idVehicle)
    }
}