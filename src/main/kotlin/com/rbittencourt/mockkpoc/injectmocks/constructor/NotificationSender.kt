package com.rbittencourt.mockkpoc.injectmocks.constructor

import org.springframework.stereotype.Component

@Component
class NotificationSender(
    private val emailSender: EmailSender,
    private val smsSender: SmsSender
) {

    fun sendNotification(email: String, phone: String): Boolean {
        val emailSent = emailSender.sendEmail(email)

        if (emailSent) {
            return smsSender.sendSms(phone)
        }

        return false
    }

}