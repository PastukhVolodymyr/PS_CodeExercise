package com.example.ps_codeexercise.data.repository

import com.example.ps_codeexercise.data.dto.ShipmentDTO

interface ShipmentsRepository {

    suspend fun shipmentsData(): List<ShipmentDTO>
}