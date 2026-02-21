package com.abrsoftware.fletiotestapp.domain.error

import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import kotlin.math.pow

/**
 * Manejo centralizado de errores con logging y retry logic
 */
sealed class AppError(
    open val message: String,
    open val throwable: Throwable? = null
) {
    data class NetworkError(
        override val message: String = "Error de conexión de red",
        override val throwable: Throwable? = null,
        val isTimeout: Boolean = false
    ) : AppError(message, throwable)

    data class ApiError(
        override val message: String,
        override val throwable: Throwable? = null,
        val code: Int? = null
    ) : AppError(message, throwable)

    data class ValidationError(
        override val message: String,
        override val throwable: Throwable? = null
    ) : AppError(message, throwable)

    data class UnknownError(
        override val message: String = "Error desconocido",
        override val throwable: Throwable? = null
    ) : AppError(message, throwable)
}

object ErrorHandler {
    fun getErrorMessage(throwable: Throwable?): String {
        return when (throwable) {
            is SocketTimeoutException -> {
                Timber.w("Timeout error: ${throwable.message}")
                "Tiempo de espera agotado. Verifique su conexión"
            }
            is IOException -> {
                Timber.w("IO error: ${throwable.message}")
                "Error de conexión. Intente más tarde"
            }
            is IllegalArgumentException -> {
                Timber.w("Validation error: ${throwable.message}")
                throwable.message ?: "Datos inválidos"
            }
            else -> {
                Timber.e(throwable, "Unknown error")
                throwable?.message ?: "Algo salió mal"
            }
        }
    }

    fun getErrorType(throwable: Throwable?): AppError {
        return when (throwable) {
            is SocketTimeoutException -> AppError.NetworkError(
                isTimeout = true,
                throwable = throwable
            )
            is IOException -> AppError.NetworkError(
                throwable = throwable
            )
            is IllegalArgumentException -> AppError.ValidationError(
                message = throwable.message ?: "Validación fallida",
                throwable = throwable
            )
            else -> AppError.UnknownError(throwable = throwable)
        }
    }

    fun shouldRetry(throwable: Throwable?): Boolean {
        return throwable is IOException || throwable is SocketTimeoutException
    }
}

/**
 * Retry logic con exponential backoff
 */
class RetryPolicy(
    val maxRetries: Int = 3,
    val initialDelayMs: Long = 100L,
    val maxDelayMs: Long = 10000L
) {
    fun getDelayMs(retryCount: Int): Long {
        val baseDelay = initialDelayMs * (powLong(2L, retryCount))
        return minOf(baseDelay, maxDelayMs)
    }

    private fun powLong(base: Long, exp: Int): Long {
        return (base.toDouble().pow(exp.toDouble())).toLong()
    }

    fun shouldRetry(retryCount: Int, throwable: Throwable?): Boolean {
        return retryCount < maxRetries && ErrorHandler.shouldRetry(throwable)
    }
}
