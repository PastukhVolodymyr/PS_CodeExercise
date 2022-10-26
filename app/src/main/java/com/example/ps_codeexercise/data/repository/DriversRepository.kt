package com.example.ps_codeexercise.data.repository

import com.example.ps_codeexercise.data.dto.DriverDTO

interface DriversRepository {

    suspend fun driversData(): List<DriverDTO>
}