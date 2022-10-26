package com.example.ps_codeexercise.domain.usecase

import com.example.ps_codeexercise.data.repository.DriversRepository
import com.example.ps_codeexercise.data.repository.ShipmentsRepository
import com.example.ps_codeexercise.domain.entity.DriverEntity
import com.example.ps_codeexercise.domain.entity.RouteEntity
import com.example.ps_codeexercise.domain.entity.ShipmentEntity
import com.example.ps_codeexercise.domain.mappers.mapToBestRouteEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RouteUseCaseImplGet(
    private val driversRepository: DriversRepository,
    private val shipmentsRepository: ShipmentsRepository,
    private val dispatcher: CoroutineDispatcher
) : GetRoutesUseCase {

    override suspend operator fun invoke(): List<RouteEntity> = withContext(dispatcher) {
        val drivers = driversRepository.driversData().map { DriverEntity(it.name) }
        val shipments = shipmentsRepository.shipmentsData().map { ShipmentEntity(it.title) }

        shipments.mapToBestRouteEntity(drivers)
    }
}
