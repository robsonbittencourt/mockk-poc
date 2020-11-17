package com.rbittencourt.mockkpoc.injectmocks.autowired

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class NotificationSender {

    @Autowired
    private lateinit var emailSender: EmailSender

    @Autowired
    private lateinit var smsSender: SmsSender

    fun sendNotification(email: String, phone: String): Boolean {
        val emailSent = emailSender.sendEmail(email)

        if (emailSent) {
            return smsSender.sendSms(phone)
        }

        return false
    }

}