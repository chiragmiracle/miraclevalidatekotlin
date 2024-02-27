package com.miracle.validationutility

import android.util.Patterns
import java.util.regex.Pattern

object Validation {

    fun validatePhoneNumber(phone: String): Boolean {
        return if (phone == null || phone.isEmpty()) {
            false
        } else Patterns.PHONE.matcher(phone).matches()
    }

    fun validateEmail(email: String?): Boolean {
        return if (email == null || email.isEmpty()) {
            false
        } else Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun generatePassword(length: Int, includeCapital: Boolean, includeSmall: Boolean, includeNumber: Boolean, includeSpecial: Boolean): String {
        val charPool = mutableListOf<Char>()
        if (includeCapital) charPool.addAll(('A'..'Z'))
        if (includeSmall) charPool.addAll(('a'..'z'))
        if (includeNumber) charPool.addAll(('0'..'9'))
        if (includeSpecial) charPool.addAll("!@#$%^&*()_+-=[]|,./?><".toList())

        return (1..length)
            .map { _ -> charPool.random() }
            .joinToString("")
    }
}