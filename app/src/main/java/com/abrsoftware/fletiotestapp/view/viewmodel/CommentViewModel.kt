package com.abrsoftware.fletiotestapp.view.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.abrsoftware.fletiotestapp.domain.repository.CommentRepository
import javax.inject.Inject
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.abrsoftware.fletiotestapp.domain.util.Resource
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
            state = state.copy(
                isLoading = true,
                error = null
            )

            when (val result = repository.getComments(idVehicle)) {
                is Resource.Success -> {
                    state = state.copy(
                        commentList = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        commentList = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}