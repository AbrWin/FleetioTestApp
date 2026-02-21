package com.abrsoftware.fletiotestapp.view.util

data class UiState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: UiError? = null,
    val isSuccess: Boolean = false
) {
    companion object {
        fun <T> idle(): UiState<T> = UiState()
        fun <T> loading(): UiState<T> = UiState(isLoading = true)
        fun <T> success(data: T): UiState<T> = UiState(data = data, isSuccess = true)
        fun <T> error(message: String, errorType: String = "unknown"): UiState<T> = 
            UiState(error = UiError(message, errorType))
    }
}

data class UiError(
    val message: String,
    val type: String = "unknown"
)
