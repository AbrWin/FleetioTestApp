package com.abrsoftware.fletiotestapp.domain.validation

/**
 * Validadores centralizados para todos los inputs de la aplicación
 */
object InputValidators {
    
    /**
     * Valida si un ID de vehículo es válido
     */
    fun validateVehicleId(id: String?): ValidationResult {
        return when {
            id.isNullOrBlank() -> ValidationResult.Invalid("ID de vehículo no puede estar vacío")
            id.length < 1 -> ValidationResult.Invalid("ID de vehículo demasiado corto")
            else -> ValidationResult.Valid
        }
    }
    
    /**
     * Valida si un string no está vacío
     */
    fun validateNotBlank(value: String?, fieldName: String = "Campo"): ValidationResult {
        return if (value.isNullOrBlank()) {
            ValidationResult.Invalid("$fieldName no puede estar vacío")
        } else {
            ValidationResult.Valid
        }
    }
    
    /**
     * Valida la página para paginación
     */
    fun validatePageNumber(page: Int, minPage: Int = 1): ValidationResult {
        return if (page < minPage) {
            ValidationResult.Invalid("Número de página inválido")
        } else {
            ValidationResult.Valid
        }
    }
    
    /**
     * Valida un email
     */
    fun validateEmail(email: String?): ValidationResult {
        if (email.isNullOrBlank()) {
            return ValidationResult.Invalid("Email no puede estar vacío")
        }
        
        val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
        return if (emailPattern.matches(email)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid("Email inválido")
        }
    }
    
    /**
     * Valida la longitud mínima de un string
     */
    fun validateMinLength(value: String?, minLength: Int, fieldName: String = "Campo"): ValidationResult {
        return when {
            value.isNullOrEmpty() -> ValidationResult.Invalid("$fieldName no puede estar vacío")
            value.length < minLength -> ValidationResult.Invalid("$fieldName debe tener al menos $minLength caracteres")
            else -> ValidationResult.Valid
        }
    }
    
    /**
     * Valida la longitud máxima de un string
     */
    fun validateMaxLength(value: String?, maxLength: Int, fieldName: String = "Campo"): ValidationResult {
        return when {
            value == null -> ValidationResult.Valid
            value.length > maxLength -> ValidationResult.Invalid("$fieldName no debe exceder $maxLength caracteres")
            else -> ValidationResult.Valid
        }
    }
}
