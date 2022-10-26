package com.example.ps_codeexercise.data.repository

import com.example.ps_codeexercise.data.dto.ShipmentDTO
import com.example.ps_codeexercise.data.source.ShipmentsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ShipmentsRepositoryImpl(
    private val dataSource: ShipmentsDataSource,
    private val dispatcher: CoroutineDispatcher
) : ShipmentsRepository {

    override suspend fun shipmentsData(): List<ShipmentDTO> =
        withContext(dispatcher) {
            dataSource.getShipmentsData()?.map { ShipmentDTO(it) } ?: emptyList()
        }
}