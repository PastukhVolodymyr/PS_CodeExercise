package com.example.ps_codeexercise.data.repository

import com.example.ps_codeexercise.data.dto.DriverDTO
import com.example.ps_codeexercise.data.source.DriversDataSource
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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
internal class DriversRepositoryImplTest {

    private val dataSource = mockk<DriversDataSource>()
    private lateinit var driverRepository: DriversRepository


    @BeforeEach
    fun setUp() = runTest {
        driverRepository =
            DriversRepositoryImpl(dataSource, UnconfinedTestDispatcher(testScheduler))
        every { dataSource.getDriversData() } returns listOf("Driver1", "Driver2", "Driver3")
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
        stopKoin()
    }

    @Test
    fun `test repository get and process drivers list`() = runTest {
        val drivers = driverRepository.driversData()

        drivers shouldHaveSize 3
    }

    @Test
    fun `test repository get and process driver model`() = runTest {
        val drivers = driverRepository.driversData()
        val expectedDriverDTO = DriverDTO("Driver1")
        drivers shouldHaveAtLeastSize 1
        drivers.first() shouldBe expectedDriverDTO
    }

    @Test
    fun `test repository get and process drivers list null`() = runTest {
        every { dataSource.getDriversData() } returns null
        val drivers = driverRepository.driversData()

        drivers shouldNotBe null
        drivers shouldBe emptyList()
    }

    @Test
    fun `test repository get and process drivers list empty`() = runTest {
        every { dataSource.getDriversData() } returns listOf()
        val drivers = driverRepository.driversData()

        drivers shouldNotBe null
        drivers shouldBe emptyList()
    }

}