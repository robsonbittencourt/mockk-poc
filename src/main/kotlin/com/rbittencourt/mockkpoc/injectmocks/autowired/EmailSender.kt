package com.rbittencourt.mockkpoc.injectmocks.autowired

interface EmailSender {

    fun sendEmail(email: String): Boolean

}