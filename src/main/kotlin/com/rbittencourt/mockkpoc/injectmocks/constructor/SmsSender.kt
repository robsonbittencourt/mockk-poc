package com.rbittencourt.mockkpoc.injectmocks.constructor

interface SmsSender {

    fun sendSms(phone: String): Boolean

}