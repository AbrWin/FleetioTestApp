package com.abrsoftware.fletiotestapp.domain.util

object ErrorHandler {
    
    fun getErrorMessage(exception: Throwable): String {
        return when (exception) {
            is AppException.NetworkException -> exception.errorMessage
            is AppException.ApiException -> exception.errorMessage
            is AppException.ParseException -> exception.errorMessage
            is AppException.ValidationException -> exception.errorMessage
            is AppException.UnknownException -> exception.errorMessage
            is java.net.SocketException -> "Sin conexión a internet"
            is java.net.ConnectException -> "No se pudo conectar al servidor"
            is java.io.IOException -> "Error de conexión"
            is retrofit2.HttpException -> {
                when (exception.code()) {
                    401 -> "No autorizado. Verifica tus credenciales"
                    403 -> "Acceso prohibido"
                    404 -> "Recurso no encontrado"
                    500 -> "Error del servidor"
                    else -> "Error HTTP: ${exception.code()}"
                }
            }
            else -> exception.message ?: "Error desconocido"
        }
    }
    
    fun getErrorType(exception: Throwable): ErrorType {
        return when (exception) {
            is AppException.NetworkException -> ErrorType.NETWORK
            is AppException.ApiException -> ErrorType.API
            is AppException.ParseException -> ErrorType.PARSING
            is AppException.ValidationException -> ErrorType.VALIDATION
            is java.net.SocketException -> ErrorType.NETWORK
            is java.net.ConnectException -> ErrorType.NETWORK
            is java.io.IOException -> ErrorType.NETWORK
            is retrofit2.HttpException -> ErrorType.API
            else -> ErrorType.UNKNOWN
        }
    }
}

enum class ErrorType {
    NETWORK, API, PARSING, VALIDATION, UNKNOWN
}
