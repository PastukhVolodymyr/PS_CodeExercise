package com.example.ps_codeexercise.domain.usecase

import com.example.ps_codeexercise.data.dto.DriverDTO
import com.example.ps_codeexercise.data.dto.ShipmentDTO
import com.example.ps_codeexercise.data.repository.DriversRepository
import com.example.ps_codeexercise.data.repository.ShipmentsRepository
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
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
internal class RouteUseCaseImplGetTest {

    private val driversListDTO = listOf(DriverDTO("Driver1"), DriverDTO("Driver11"))
    private val shipmentListDTO = listOf(ShipmentDTO("Shipment1"), ShipmentDTO("Shimpent12"))

    private val driversRepository = mockk<DriversRepository>()
    private val shipmentsRepository = mockk<ShipmentsRepository>()

    private lateinit var getRoutesUseCase: GetRoutesUseCase


    @BeforeEach
    fun setUp() = runTest {
        getRoutesUseCase =
            RouteUseCaseImplGet(
                driversRepository,
                shipmentsRepository,
                UnconfinedTestDispatcher(testScheduler)
            )
        coEvery { driversRepository.driversData() } returns driversListDTO
        coEvery { shipmentsRepository.shipmentsData() } returns shipmentListDTO
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
        stopKoin()
    }

    @Test
    fun `test use case invocation and process routes list`() = runTest {
        val drivers = getRoutesUseCase()

        drivers shouldHaveSize shipmentListDTO.size
    }

    @Test
    fun `test use case invocation and process routes list empty`() = runTest {
        coEvery { driversRepository.driversData() } returns emptyList()
        coEvery { shipmentsRepository.shipmentsData() } returns emptyList()
        val drivers = getRoutesUseCase()

        drivers shouldBe emptyList()
    }

    @Test
    fun `test use case invocation and process routes with empty driver list`() = runTest {
        coEvery { driversRepository.driversData() } returns emptyList()
        val drivers = getRoutesUseCase()

        drivers shouldHaveSize shipmentListDTO.size
    }

}