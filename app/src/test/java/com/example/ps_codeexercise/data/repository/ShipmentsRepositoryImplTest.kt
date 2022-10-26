package com.example.ps_codeexercise.data.repository

import com.example.ps_codeexercise.data.dto.ShipmentDTO
import com.example.ps_codeexercise.data.source.ShipmentsDataSource
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.stopKoin
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
internal class ShipmentsRepositoryImplTest {

    private val dataSource = mockk<ShipmentsDataSource>()
    private lateinit var shipmentsRepository: ShipmentsRepository


    @BeforeEach
    fun setUp() = runTest {
        shipmentsRepository =
            ShipmentsRepositoryImpl(dataSource, UnconfinedTestDispatcher(testScheduler))
        every { dataSource.getShipmentsData() } returns listOf(
            "Shipment1",
            "Shipment2",
            "Shipment3"
        )
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
        stopKoin()
    }

    @Test
    fun `test repository get and process shipments list`() = runTest {
        val drivers = shipmentsRepository.shipmentsData()

        drivers shouldHaveSize 3
    }

    @Test
    fun `test repository get and process shipments model`() = runTest {
        val drivers = shipmentsRepository.shipmentsData()
        val expectedDriverDTO = ShipmentDTO("Shipment1")

        drivers shouldHaveAtLeastSize 1
        drivers.first() shouldBe expectedDriverDTO
    }

    @Test
    fun `test repository get and process shipments list null`() = runTest {
        every { dataSource.getShipmentsData() } returns null
        val drivers = shipmentsRepository.shipmentsData()

        drivers shouldNotBe null
        drivers shouldBe emptyList()
    }

    @Test
    fun `test repository get and process shipments list empty`() = runTest {
        every { dataSource.getShipmentsData() } returns listOf()
        val drivers = shipmentsRepository.shipmentsData()

        drivers shouldNotBe null
        drivers shouldBe emptyList()
    }

}