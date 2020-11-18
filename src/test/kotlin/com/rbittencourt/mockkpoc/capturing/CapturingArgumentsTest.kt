package com.rbittencourt.mockkpoc.capturing

import com.rbittencourt.mockkpoc.injectmocks.autowired.EmailSender
import com.rbittencourt.mockkpoc.injectmocks.autowired.NotificationSender
import com.rbittencourt.mockkpoc.injectmocks.autowired.SmsSender
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CapturingArgumentsTest {

    @InjectMockKs
    lateinit var notificationSender: NotificationSender

    @MockK
    lateinit var emailSender: EmailSender

    @MockK
    lateinit var smsSender: SmsSender

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this, relaxed = true)

    @Test
    fun `should capture email passed to EmailSender`() {
        val email = "test@test.com"

        val emailSlot = slot<String>()
        every { emailSender.sendEmail(capture(emailSlot)) } returns true

        notificationSender.sendNotification(email, "111222333")

        assertEquals(email, emailSlot.captured)
    }

    @Test
    fun `should capture all emails passed to EmailSender`() {
        val emailOne = "test.one@test.com"
        val emailTwo = "test.two@test.com"
        val emailThree = "test.three@test.com"

        val emailSlot = mutableListOf<String>()
        every { emailSender.sendEmail(capture(emailSlot)) } returns true

        notificationSender.sendNotification(emailOne, "111222333")
        notificationSender.sendNotification(emailTwo, "111222333")
        notificationSender.sendNotification(emailThree, "111222333")

        assertEquals(emailOne, emailSlot[0])
        assertEquals(emailTwo, emailSlot[1])
        assertEquals(emailThree, emailSlot[2])
    }

}