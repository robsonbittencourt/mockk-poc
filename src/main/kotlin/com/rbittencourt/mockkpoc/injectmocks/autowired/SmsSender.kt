package com.rbittencourt.mockkpoc.injectmocks.autowired

interface SmsSender {

    fun sendSms(phone: String): Boolean

}