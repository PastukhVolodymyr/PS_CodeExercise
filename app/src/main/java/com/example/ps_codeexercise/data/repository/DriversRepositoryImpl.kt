package com.example.ps_codeexercise.data.repository

import com.example.ps_codeexercise.data.dto.DriverDTO
import com.example.ps_codeexercise.data.source.DriversDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DriversRepositoryImpl(
    private val dataSource: DriversDataSource,
    private val dispatcher: CoroutineDispatcher
) : DriversRepository {

    override suspend fun driversData(): List<DriverDTO> =
        withContext(dispatcher) {
            dataSource.getDriversData()?.map { DriverDTO(it) } ?: emptyList()
        }
}