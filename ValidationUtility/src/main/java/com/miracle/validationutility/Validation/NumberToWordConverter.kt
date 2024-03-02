package com.miracle.validationutility.Validation

object NumberToWordConverter {

    private val units =
        arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
    private val teens = arrayOf(
        "",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen"
    )
    private val tens = arrayOf(
        "",
        "Ten",
        "Twenty",
        "Thirty",
        "Forty",
        "Fifty",
        "Sixty",
        "Seventy",
        "Eighty",
        "Ninety"
    )
    private val thousands = arrayOf("", "Thousand", "Million", "Billion", "Trillion")

    fun convertCountToWord(number: String?): String? {
        return if (number == null || number.isEmpty()) "Please enter a valid number" else try {
            var num = number.toLong()
            if (num == 0L) return "Zero"
            var scale = 0
            val result = StringBuilder()
            while (num > 0) {
                if (num % 1000 != 0L) {
                    val groupResult = StringBuilder()
                    convertThreeDigitsToWord((num % 1000).toInt(), groupResult)
                    result.insert(0, groupResult.append(thousands[scale]).append(" "))
                }
                num /= 1000
                scale++
            }
            result.toString().trim { it <= ' ' }
        } catch (e: NumberFormatException) {
            "Invalid number format"
        }
    }

    private fun convertThreeDigitsToWord(number: Int, result: StringBuilder) {
        var number = number
        if (number >= 100) {
            result.append(units[number / 100]).append(" Hundred ")
            number %= 100
        }
        if (number >= 11 && number <= 19) {
            result.append(teens[number - 10]).append(" ")
        } else if (number >= 20 || number == 10) {
            result.append(tens[number / 10]).append(" ")
            number %= 10
        }
        if (number > 0 && number < 10) {
            result.append(units[number]).append(" ")
        }
    }

}