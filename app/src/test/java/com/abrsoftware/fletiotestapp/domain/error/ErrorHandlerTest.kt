package com.abrsoftware.fletiotestapp.domain.error

import org.junit.Test
import org.junit.Assert.*
import java.io.IOException
import java.net.SocketTimeoutException

class ErrorHandlerTest {

    @Test
    fun testGetErrorMessageForNetworkError() {
        val exception = IOException("Connection failed")
        val message = ErrorHandler.getErrorMessage(exception)
        assertTrue(message.contains("conexión"))
    }

    @Test
    fun testGetErrorMessageForTimeoutError() {
        val exception = SocketTimeoutException("Timeout")
        val message = ErrorHandler.getErrorMessage(exception)
        assertTrue(message.contains("Tiempo de espera"))
    }

    @Test
    fun testShouldRetryForIOException() {
        val exception = IOException("Connection failed")
        assertTrue(ErrorHandler.shouldRetry(exception))
    }

    @Test
    fun testShouldRetryForTimeoutException() {
        val exception = SocketTimeoutException("Timeout")
        assertTrue(ErrorHandler.shouldRetry(exception))
    }

    @Test
    fun testShouldNotRetryForValidationError() {
        val exception = IllegalArgumentException("Invalid input")
        assertFalse(ErrorHandler.shouldRetry(exception))
    }
}
