package com.rbittencourt.mockkpoc.injectmocks.constructor

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NotificationSenderTest {

    @MockK
    lateinit var emailSender: EmailSender

    @MockK
    lateinit var smsSender: SmsSender

    private lateinit var notificationSender: NotificationSender

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        notificationSender = NotificationSender(emailSender, smsSender)
    }

    @Test
    fun `should return true when email and sms are sent`() {
        val email = "test@test.com"
        val phone = "111222333"

        every { emailSender.sendEmail(email) } returns true
        every { smsSender.sendSms(phone) } returns true

        val notificationResult = notificationSender.sendNotification(email, phone)

        assertTrue(notificationResult)
    }

    @Test
    fun `should return false when email is not sent and don't try send sms`() {
        val email = "test@test.com"
        val phone = "111222333"

        every { emailSender.sendEmail(email) } returns false

        val notificationResult = notificationSender.sendNotification(email, phone)

        assertFalse(notificationResult)
        verify(exactly = 0)  { smsSender.sendSms(phone) }
    }

    @Test
    fun `should return false when email is sent but sms don't`() {
        val email = "test@test.com"
        val phone = "111222333"

        every { emailSender.sendEmail(email) } returns true
        every { smsSender.sendSms(phone) } returns false

        val notificationResult = notificationSender.sendNotification(email, phone)

        assertFalse(notificationResult)
    }

}