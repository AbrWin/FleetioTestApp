package com.abrsoftware.fletiotestapp.domain.util

sealed class AppException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    
    data class NetworkException(val errorMessage: String) : 
        AppException("Error de red: $errorMessage")
    
    data class ApiException(val code: Int, val errorMessage: String) : 
        AppException("Error API ($code): $errorMessage")
    
    data class ParseException(val errorMessage: String) : 
        AppException("Error al parsear datos: $errorMessage")
    
    data class ValidationException(val errorMessage: String) : 
        AppException("Validación fallida: $errorMessage")
    
    data class UnknownException(val errorMessage: String) : 
        AppException("Error desconocido: $errorMessage")
}
