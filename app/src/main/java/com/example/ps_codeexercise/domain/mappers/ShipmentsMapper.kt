package com.example.ps_codeexercise.domain.mappers

import com.example.ps_codeexercise.domain.entity.DriverEntity
import com.example.ps_codeexercise.domain.entity.RouteEntity
import com.example.ps_codeexercise.domain.entity.ShipmentEntity
import com.example.ps_codeexercise.domain.entity.StringAttributes

fun List<ShipmentEntity>.mapToBestRouteEntity(drivers: List<DriverEntity>): List<RouteEntity> {
    val availableDrivers = drivers.toMutableList()
    return this.map { shipment ->
        val shipmentAttr = StringAttributes(shipment.title)
        val matchDriver = when {
            shipmentAttr.isEven -> availableDrivers.maxByOrNull { driver ->
                StringAttributes(driver.name).ssEvenTo(shipmentAttr)
            }

            shipmentAttr.isOdd -> availableDrivers.maxByOrNull { driver ->
                StringAttributes(driver.name).ssOddTo(shipmentAttr)
            }

            else -> null
        }

        availableDrivers.remove(matchDriver)
        RouteEntity(matchDriver ?: DriverEntity.undefinedDriver(), shipment)
    }
}