package com.example.ps_codeexercise.domain.entity

import com.example.ps_codeexercise.domain.utils.commonFactor

private const val EVEN_COEFFICIENT = 1.5
private const val ODD_COEFFICIENT = 1.0
private const val COMMON_FACTOR_COEFFICIENT = 1.5
private const val NO_COEFFICIENT = 1.0

private const val LOWEST_COMMON_FACTOR = 2

private val VOWELS = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

data class StringAttributes(private val string: String) {
    val isEven = (string.length % 2) == 0
    val isOdd = isEven.not()

    private val length = string.length

    private val vowelsAmount = string.sumOf { (if (it in VOWELS) 1 else 0).toInt() }
    private val consonantsAmount = string.sumOf { (if (it in VOWELS + ' ') 0 else 1).toInt() }

    private fun commonFactorWith(another: StringAttributes): Boolean =
        commonFactor(length, another.length) >= LOWEST_COMMON_FACTOR

    fun ssEvenTo(another: StringAttributes) =
        vowelsAmount * EVEN_COEFFICIENT *
                if (commonFactorWith(another)) COMMON_FACTOR_COEFFICIENT else NO_COEFFICIENT

    fun ssOddTo(another: StringAttributes) =
        consonantsAmount * ODD_COEFFICIENT *
                if (commonFactorWith(another)) COMMON_FACTOR_COEFFICIENT else NO_COEFFICIENT
}