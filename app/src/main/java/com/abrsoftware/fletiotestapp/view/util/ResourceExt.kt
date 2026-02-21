package com.abrsoftware.fletiotestapp.view.util

import com.abrsoftware.fletiotestapp.domain.util.Resource

suspend inline fun <T, R> executeWithErrorHandling(
    block: suspend () -> T,
    onSuccess: (T) -> R,
    onError: (String) -> R
): R {
    return try {
        val result = block()
        onSuccess(result)
    } catch (e: Exception) {
        onError(e.message ?: "Error desconocido")
    }
}

fun <T> Resource<T>.getOrNull(): T? = when (this) {
    is Resource.Success -> data
    is Resource.Error -> null
}

fun <T> Resource<T>.getError(): String? = when (this) {
    is Resource.Success -> null
    is Resource.Error -> message
}
