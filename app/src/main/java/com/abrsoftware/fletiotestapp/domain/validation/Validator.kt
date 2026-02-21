package com.abrsoftware.fletiotestapp.domain.validation

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val message: String) : ValidationResult()
}

interface Validator {
    fun validate(): ValidationResult
}
