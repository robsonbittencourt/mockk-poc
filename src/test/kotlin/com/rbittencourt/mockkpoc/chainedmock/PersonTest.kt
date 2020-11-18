package com.rbittencourt.mockkpoc.chainedmock

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    fun `should return mocked address number using chained mock`() {
        val person = mockk<Person>()

        every { person.address.number } returns 10

        assertEquals(10, person.address.number)
    }

}