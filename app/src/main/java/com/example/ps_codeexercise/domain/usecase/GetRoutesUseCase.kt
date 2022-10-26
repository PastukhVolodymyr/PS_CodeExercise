package com.example.ps_codeexercise.domain.usecase

import com.example.ps_codeexercise.domain.entity.RouteEntity

interface GetRoutesUseCase {

    suspend operator fun invoke(): List<RouteEntity>
}

