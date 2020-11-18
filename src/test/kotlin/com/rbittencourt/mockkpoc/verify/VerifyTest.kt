package com.rbittencourt.mockkpoc.verify

import com.rbittencourt.mockkpoc.injectmocks.autowired.EmailSender
import com.rbittencourt.mockkpoc.injectmocks.autowired.NotificationSender
import com.rbittencourt.mockkpoc.injectmocks.autowired.SmsSender
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class VerifyTest {

    @InjectMockKs
    lateinit var notificationSender: NotificationSender

    @MockK
    lateinit var emailSender: EmailSender

    @MockK
    lateinit var smsSender: SmsSender

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this, relaxed = true)

    @Test
    fun `should verify if EmailSender was called`() {
        val email = "test@test.com"

        notificationSender.sendNotification(email, "111222333")

        verify { emailSender.sendEmail(email) }
    }

    @Test
    fun `should verify if informed email starts with test`() {
        val email = "test@test.com"

        notificationSender.sendNotification(email, "111222333")

        verify { emailSender.sendEmail(match { it.startsWith("test") }) }
    }

    @Test
    fun `should verify if EmailSender was called three times`() {
        val email = "test@test.com"

        notificationSender.sendNotification(email, "111222333")
        notificationSender.sendNotification(email, "111222333")
        notificationSender.sendNotification(email, "111222333")

        verify(exactly = 3) { emailSender.sendEmail(email) }
    }

    @Test
    fun `should verify if EmailSender was called three times in correct order`() {
        val emailOne = "test.one@test.com"
        val emailTwo = "test.two@test.com"
        val emailThree = "test.three@test.com"

        notificationSender.sendNotification(emailOne, "111222333")
        notificationSender.sendNotification(emailTwo, "111222333")
        notificationSender.sendNotification(emailThree, "111222333")

        verifyOrder {
            emailSender.sendEmail(emailOne)
            emailSender.sendEmail(emailTwo)
            emailSender.sendEmail(emailThree)
        }
    }

}