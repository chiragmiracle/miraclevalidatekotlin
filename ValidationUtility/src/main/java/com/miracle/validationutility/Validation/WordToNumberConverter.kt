package com.miracle.validationutility.Validation

object WordToNumberConverter {

    private val NUMBER_WORDS_MAP = hashMapOf(
        "zero" to 0L,
        "one" to 1L,
        "two" to 2L,
        "three" to 3L,
        "four" to 4L,
        "five" to 5L,
        "six" to 6L,
        "seven" to 7L,
        "eight" to 8L,
        "nine" to 9L,
        "ten" to 10L,
        "eleven" to 11L,
        "twelve" to 12L,
        "thirteen" to 13L,
        "fourteen" to 14L,
        "fifteen" to 15L,
        "sixteen" to 16L,
        "seventeen" to 17L,
        "eighteen" to 18L,
        "nineteen" to 19L,
        "twenty" to 20L,
        "thirty" to 30L,
        "forty" to 40L,
        "fifty" to 50L,
        "sixty" to 60L,
        "seventy" to 70L,
        "eighty" to 80L,
        "ninety" to 90L,
        "hundred" to 100L,
        "thousand" to 1000L,
        "million" to 1000000L,
        "billion" to 1000000000L
    )

    fun convertWordToNumber(words: String): Long {
        if (words.isBlank()) return -1

        var result = 0L
        var tempResult = 0L
        var scale = 1L

        val parts = words.trim().split("\\s+".toRegex()).map { it.toLowerCase() }

        for (part in parts) {
            if (part == "and") continue
            if (!NUMBER_WORDS_MAP.containsKey(part)) return -1

            val number = NUMBER_WORDS_MAP[part] ?: return -1

            when {
                number == 100L -> tempResult *= number
                number >= 1000L -> {
                    result += tempResult * number
                    tempResult = 0
                    scale *= number
                }
                else -> tempResult += number
            }
        }

        return result + tempResult
    }
}