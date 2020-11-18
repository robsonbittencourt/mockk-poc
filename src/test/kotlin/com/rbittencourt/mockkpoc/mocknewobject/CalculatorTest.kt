package com.rbittencourt.mockkpoc.mocknewobject

import io.mockk.every
import io.mockk.mockkConstructor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun `should sum values using mock on real object but subtract with real implementation`() {
        mockkConstructor(Calculator::class)
        every { anyConstructed<Calculator>().sum(any(), any()) } returns 2

        val calculator = Calculator()
        val resultSum = calculator.sum(10, 20)
        val resultSubtraction = calculator.subtract(30, 15)

        assertEquals(2, resultSum)
        assertEquals(15, resultSubtraction)
    }

}