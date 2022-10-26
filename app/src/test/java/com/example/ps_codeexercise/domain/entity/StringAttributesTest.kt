package com.example.ps_codeexercise.domain.entity


import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class StringAttributesTest {

    private val testString = "test string"
    private val testAnotherString = "test another string"
    private lateinit var stringAttr: StringAttributes
    private lateinit var stringAnotherAttr: StringAttributes

    @BeforeEach
    fun setUp() {
        stringAttr = StringAttributes(testString)
        stringAnotherAttr = StringAttributes(testAnotherString)
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `is string length even`() {
        stringAttr.isEven shouldBe false
    }

    @Test
    fun `is string length odd`() {
        stringAttr.isOdd shouldBe true
    }

    @Test
    fun `calculate score even`() {
        val expected = 3
        stringAttr.ssEvenTo(stringAnotherAttr) shouldBe expected
    }

    @Test
    fun `calculate score odd`() {
        val expected = 8
        stringAttr.ssOddTo(stringAnotherAttr) shouldBe expected
    }
}