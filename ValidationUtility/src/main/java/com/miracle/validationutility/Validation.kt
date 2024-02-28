package com.miracle.validationutility

import android.util.Patterns


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

    fun volumeConvert(value: Double, factorFrom: String, factorTo: String): Double {
        val conversionFactors = mapOf(
            "US liquid gallon" to 1.0,
            "US liquid quart" to 4.0,
            "US liquid pint" to 8.0,
            "US legal cup" to 15.7725,
            "US fluid ounce" to 128.0,
            "US tablespoon" to 256.0,
            "US teaspoon" to 768.0,
            "cubic meter" to 0.00378541,
            "liter" to 3.78541,
            "milliliter" to 3785.41,
            "imperial gallon" to 0.832674,
            "imperial quart" to 3.3307,
            "imperial pint" to 6.66139,
            "imperial cup" to 13.3228,
            "imperial fluid ounce" to 133.228,
            "imperial tablespoon" to 213.165,
            "imperial teaspoon" to 639.494,
            "cubic foot" to 0.133681,
            "cubic inch" to 231.0
        )
        val unitFrom = conversionFactors[factorFrom]
        val unitTo = conversionFactors[factorTo]

        if (unitFrom == null || unitTo == null) {
            return -1.0
        }
        return value * (unitTo / unitFrom)
    }
}