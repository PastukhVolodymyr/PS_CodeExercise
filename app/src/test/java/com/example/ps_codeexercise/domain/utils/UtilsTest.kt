package com.example.ps_codeexercise.domain.utils

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class UtilsTest {

    @Test
    fun `calculate with simple numbers`() {
        val expected = 1
        val actual = commonFactor(11, 2352)

        actual shouldBe expected
    }

    @Test
    fun `calculate with even numbers`() {
        val expected = 12
        val actual = commonFactor(12, 144)

        actual shouldBe expected
    }

    @Test
    fun `calculate with different numbers`() {
        val expected = 6
        val actual = commonFactor(18, 6)

        actual shouldBe expected
    }

    @Test
    fun `calculate with zero`() {
        val expected = 144
        val actual = commonFactor(0, 144)

        actual shouldBe expected
    }
}