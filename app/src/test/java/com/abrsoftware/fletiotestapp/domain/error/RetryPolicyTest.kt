package com.abrsoftware.fletiotestapp.domain.error

import org.junit.Test
import org.junit.Assert.*
import java.io.IOException

class RetryPolicyTest {

    @Test
    fun testRetryPolicyMaxRetries() {
        val policy = RetryPolicy(maxRetries = 3)
        assertTrue(policy.shouldRetry(0, IOException("test")))
        assertTrue(policy.shouldRetry(1, IOException("test")))
        assertTrue(policy.shouldRetry(2, IOException("test")))
        assertFalse(policy.shouldRetry(3, IOException("test")))
    }

    @Test
    fun testExponentialBackoffDelay() {
        val policy = RetryPolicy(initialDelayMs = 100L)
        val delay0 = policy.getDelayMs(0)
        val delay1 = policy.getDelayMs(1)
        val delay2 = policy.getDelayMs(2)

        assertEquals(100L, delay0)
        assertEquals(200L, delay1)
        assertEquals(400L, delay2)
    }

    @Test
    fun testMaxDelayLimit() {
        val policy = RetryPolicy(initialDelayMs = 1000L, maxDelayMs = 5000L)
        val delay5 = policy.getDelayMs(5)
        assertTrue(delay5 <= 5000L)
    }
}
