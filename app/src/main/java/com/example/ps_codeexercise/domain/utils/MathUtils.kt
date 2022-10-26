package com.example.ps_codeexercise.domain.utils

fun commonFactor(a: Int, b: Int): Int = when {
    a < b -> gcd(a, b)
    a > b -> gcd(b, a)
    else -> a
}


private fun gcd(s: Int, b: Int): Int = if (s == 0) b else gcd(b % s, s)