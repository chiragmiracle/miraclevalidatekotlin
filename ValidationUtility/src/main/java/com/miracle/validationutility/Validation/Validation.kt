package com.miracle.validationutility.Validation

import android.util.Patterns
import java.util.Calendar

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

    fun generatePassword(
        length: Int,
        includeCapital: Boolean,
        includeSmall: Boolean,
        includeNumber: Boolean,
        includeSpecial: Boolean
    ): String {
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

    fun LengthConvert(value: Double, fromUnit: String, toUnit: String): Double {
        val conversionFactors = mapOf(
            "Kilometer" to 1000.0,
            "Meter" to 1.0,
            "Centimeter" to 0.01,
            "Millimeter" to 0.001,
            "Micrometer" to 0.000001,
            "Nanometer" to 0.000000001,
            "Mile" to 1609.344,
            "Yard" to 0.9144,
            "Foot" to 0.3048,
            "Inch" to 0.0254,
            "Nautical Mile" to 1852.0
        )

        val meterValue = value * (conversionFactors[fromUnit] ?: 1.0)
        return meterValue / (conversionFactors[toUnit] ?: 1.0)
    }

    fun MassConvert(mass_str: String, fromUnit: String, toUnit: String): Double {
        val conversionMatrix = arrayOf(
            doubleArrayOf(
                1.0,
                1000.0,
                1000000.0,
                1000000000.0,
                1000000000000.0,
                0.9842065286,
                1.1023113109,
                157.4730444178,
                2204.6226218488,
                35273.96194958
            ),
            doubleArrayOf(
                0.001,
                1.0,
                1000.0,
                1000000.0,
                1000000000.0,
                0.0009842065,
                0.0011023113,
                0.1574730444,
                2.2046226218,
                35.2739619496
            ),
            doubleArrayOf(
                0.000001,
                0.001,
                1.0,
                1000.0,
                1000000.0,
                0.0000009842,
                0.0000011023,
                0.000157473,
                0.0022046226,
                0.0352739619
            ),
            doubleArrayOf(
                0.000000001,
                0.000001,
                0.001,
                1.0,
                1000.0,
                0.000000001,
                0.0000000011,
                0.0000001575,
                0.0000022046,
                0.000035274
            ),
            doubleArrayOf(
                0.000000000001,
                0.000000001,
                0.000001,
                0.001,
                1.0,
                0.000000000001,
                0.0000000000011,
                0.0000000001575,
                0.0000000022046,
                0.000000035274
            ),
            doubleArrayOf(
                1.016e-6,
                0.0010160469,
                0.0010160469,
                1.0160469088,
                1016.0469088,
                1.0,
                1.12,
                160.0,
                2240.0,
                35840.0
            ),
            doubleArrayOf(
                0.90718474,
                907.18474,
                907184.74,
                907184740.0,
                9.0718474e+11,
                0.8928571429,
                1.0,
                142.8571428571,
                2000.0,
                32000.0
            ),
            doubleArrayOf(
                0.00635029318,
                6.35029318,
                6350.29318,
                6350293.18,
                6.35029318e+9,
                0.00625,
                0.007,
                1.0,
                14.0,
                224.0
            ),
            doubleArrayOf(
                0.00045359237,
                0.45359237,
                453.59237,
                453592.37,
                453592370.0,
                0.0004464286,
                0.0005,
                0.0714285714,
                1.0,
                16.0
            ),
            doubleArrayOf(
                0.00002834952,
                0.0283495231,
                28.349523125,
                28349.523125,
                28349523.125,
                2.7777777778e-5,
                3.125e-5,
                0.0044642857,
                0.0625,
                1.0
            )
        )
        val fromIndex = MassUnit(fromUnit)
        val toIndex = MassUnit(toUnit)
        val inputValue = mass_str.toDouble()
        return inputValue * conversionMatrix[fromIndex][toIndex]
    }

    fun MassUnit(unit: String): Int {
        return when (unit) {
            "Tonne" -> 0
            "Kilogram" -> 1
            "Gram" -> 2
            "Milligram" -> 3
            "Microgram" -> 4
            "Imperial Ton" -> 5
            "US Ton" -> 6
            "Stone" -> 7
            "Pound" -> 8
            "Ounce" -> 9
            else -> -1
        }
    }

    fun compareDateTime(selectedDateTime: Calendar, currentDateTime: Calendar): String? {
        val diffMillis = currentDateTime.timeInMillis - selectedDateTime.timeInMillis
        val seconds = diffMillis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val remainingHours = hours % 24
        val remainingMinutes = minutes % 60
        val remainingSeconds = seconds % 60
        val builder = StringBuilder()
        if (days > 0) {
            builder.append(days).append(" days, ")
        }
        if (remainingHours >= 0) {
            builder.append(remainingHours).append(" hours, ")
        }
        if (remainingMinutes >= 0) {
            builder.append(remainingMinutes).append(" minutes, ")
        }
        if (remainingSeconds >= 0) {
            builder.append(remainingSeconds).append(" seconds")
        }
        return builder.toString()
    }

    fun compareDateTimeAgo(selectedDateTime: Calendar, currentDateTime: Calendar): String? {
        val differenceInMillis = currentDateTime.timeInMillis - selectedDateTime.timeInMillis
        val secondsInMillis: Long = 1000
        val minutesInMillis = secondsInMillis * 60
        val hoursInMillis = minutesInMillis * 60
        val daysInMillis = hoursInMillis * 24
        val weeksInMillis = daysInMillis * 7
        val monthsInMillis = daysInMillis * 30
        val yearsInMillis = daysInMillis * 365
        val years = differenceInMillis / yearsInMillis
        val months = differenceInMillis / monthsInMillis
        val weeks = differenceInMillis / weeksInMillis
        val days = differenceInMillis / daysInMillis
        val hours = differenceInMillis / hoursInMillis
        val minutes = differenceInMillis / minutesInMillis
        val seconds = differenceInMillis / secondsInMillis
        return if (years > 0) {
            "$years year's ago"
        } else if (months > 0) {
            "$months month's ago"
        } else if (weeks > 0) {
            "$weeks week's ago"
        } else if (days > 0) {
            "$days day's ago"
        } else if (hours > 0) {
            "$hours hour's ago"
        } else if (minutes > 0) {
            "$minutes minute's ago"
        } else {
            "$seconds second's ago"
        }
    }
}